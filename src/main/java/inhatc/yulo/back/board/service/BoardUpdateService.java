package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.BoardUpdateRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.BoardUpdateResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.FileRepository;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardUpdateService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;
    private final String uploadDir = "C:/path/to/upload";

    @Transactional
    public BoardUpdateResponseDTO updateBoard(BoardUpdateRequestDTO boardUpdateRequestDTO) throws IOException {

        Board board = boardRepository.findById(boardUpdateRequestDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        // 글의 작성자만 수정을 할 수 있도록 검사
        if (!board.getUser().getUserId().equals(boardUpdateRequestDTO.getUserId())) {
            throw new IllegalArgumentException("Only the author can update this board");
        }

        board.setTitle(boardUpdateRequestDTO.getTitle());
        board.setContent(boardUpdateRequestDTO.getContent());
        Board updatedBoard = boardRepository.save(board);

        // 삭제할 파일 처리
        if (boardUpdateRequestDTO.getDeleteFileId() != null) {
            for (Long fileId : boardUpdateRequestDTO.getDeleteFileId()) {
                File file = fileRepository.findById(fileId)
                        .orElseThrow( () -> new IllegalArgumentException("File not found"));
                fileRepository.delete(file);
                // 파일 시스템에서 파일 삭제
                Path filePath = Paths.get(file.getFilePath());
                Files.deleteIfExists(filePath);
            }
        }

        // 새로 추가할 파일 처리
        if (boardUpdateRequestDTO.getNewFiles() != null) {
            for (MultipartFile newFile : boardUpdateRequestDTO.getNewFiles()) {
                if (!newFile.isEmpty()) {
                    String origFilename = newFile.getOriginalFilename();
                    String fileName = System.currentTimeMillis() + "_" + origFilename;
                    Path filePath = Paths.get(uploadDir, fileName);

                    // 디렉터리 생성
                    if (!Files.exists(filePath.getParent())) {
                        Files.createDirectories(filePath.getParent());
                    }
                    Files.write(filePath, newFile.getBytes());

                    File fileEntity = File.builder()
                            .origFilename(origFilename)
                            .fileName(fileName)
                            .filePath(filePath.toString())
                            .board(board)
                            .build();
                    fileRepository.save(fileEntity);
                }
            }
        }
        List<File> files = fileRepository.findByBoardId(updatedBoard.getId());



        return BoardUpdateResponseDTO.builder()
                .userName(updatedBoard.getUser().getUserName())
                .title(updatedBoard.getTitle())
                .content(updatedBoard.getContent())
                .createDate(updatedBoard.getCreateDate())
                .updateDate(updatedBoard.getModifiedDate())
                .files(files)
                .build();
    }
}

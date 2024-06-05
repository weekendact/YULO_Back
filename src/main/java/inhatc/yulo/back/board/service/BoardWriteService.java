package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.BoardWriteRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.BoardWriteResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.FileRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Service
public class BoardWriteService {

    private static final Logger logger = LoggerFactory.getLogger(BoardWriteService.class);

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final String uploadDir = "C:/path/to/upload";

    @Transactional
    public BoardWriteResponseDTO writeBoard(BoardWriteRequestDTO boardWriteRequestDTO, MultipartFile file) throws IOException {

        User user = userRepository.findById(boardWriteRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Board board = Board.builder()
                .user(user)
                .title(boardWriteRequestDTO.getTitle())
                .content(boardWriteRequestDTO.getContent())
                .build();

        Board savedBoard = boardRepository.save(board);

        if (file != null && !file.isEmpty()) {
            String origFilename = file.getOriginalFilename();
            String fileName = System.currentTimeMillis() + "_" + origFilename;
            Path filePath = Paths.get(uploadDir, fileName);

            // 디렉터리 생성
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.write(filePath, file.getBytes());

            File fileEntity = File.builder()
                    .origFilename(origFilename)
                    .fileName(fileName)
                    .filePath(filePath.toString())
                    .board(savedBoard)
                    .build();
            fileRepository.save(fileEntity);
        }
        // 파일 리스트 조회
        List<File> files = fileRepository.findByBoardId(savedBoard.getId());

            return BoardWriteResponseDTO.builder()
                    .userName(user.getUserName())
                    .title(savedBoard.getTitle())
                    .content(savedBoard.getContent())
                    .createDate(savedBoard.getCreateDate())
                    .files(files)
                    .build();
        }
}

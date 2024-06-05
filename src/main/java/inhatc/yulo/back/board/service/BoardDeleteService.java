package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.BoardDeleteRequestDTO;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    @Transactional
    public boolean deleteBoard(BoardDeleteRequestDTO boardDeleteRequestDTO) {
        Long boardId = boardDeleteRequestDTO.getBoardId();
        Long userId = boardDeleteRequestDTO.getUserId();

        // userId가 2인 사용자를 관리자로 부여하여 삭제 기능 + 해당 글이 존재하고, 작성자만 삭제할 수 있도록 하기
        if(userId == 2L || boardRepository.existsByIdAndUser_UserId(boardId, userId)) {
            // 게시글에 연관된 파일들을 삭제
            List<File> files = fileRepository.findByBoardId(boardId);
            for (File file : files) {
                // 파일 시스템에서 파일 삭제
                Path filePath = Paths.get(file.getFilePath());
                try {
                    Files.deleteIfExists(filePath);
                } catch (IOException e) {
                    // 파일 삭제 실패 시 예외 처리
                    e.printStackTrace();
                }
                // 데이터베이스에서 파일 삭제
                fileRepository.delete(file);
            }
            // 게시글 삭제
            boardRepository.deleteById(boardId);
            return true;
        } else {
            return false;
        }
    }
}

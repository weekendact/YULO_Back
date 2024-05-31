package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.BoardDeleteRequestDTO;
import inhatc.yulo.back.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {

    private final BoardRepository boardRepository;

    public boolean deleteBoard(BoardDeleteRequestDTO boardDeleteRequestDTO) {
        Long boardId = boardDeleteRequestDTO.getBoardId();
        Long userId = boardDeleteRequestDTO.getUserId();

        // userId가 2인 사용자를 관리자로 부여하여 삭제 기능 + 해당 글이 존재하고, 작성자만 삭제할 수 있도록 하기
        if(userId == 2L || boardRepository.existsByIdAndUser_UserId(boardId, userId)) {
            boardRepository.deleteById(boardId);
            return true;
        } else {
            return false;
        }
    }
}

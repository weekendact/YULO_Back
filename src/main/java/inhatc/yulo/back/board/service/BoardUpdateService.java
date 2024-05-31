package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.BoardUpdateRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.BoardUpdateResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUpdateService {

    private final BoardRepository boardRepository;

    public BoardUpdateResponseDTO updateBoard(BoardUpdateRequestDTO boardUpdateRequestDTO) {

        Board board = boardRepository.findById(boardUpdateRequestDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        // 글의 작성자만 수정을 할 수 있도록 검사
        if (!board.getUser().getUserId().equals(boardUpdateRequestDTO.getUserId())) {
            throw new IllegalArgumentException("Only the author can update this board");
        }

        board.setTitle(boardUpdateRequestDTO.getTitle());
        board.setContent(boardUpdateRequestDTO.getContent());
        Board updatedBoard = boardRepository.save(board);

        return BoardUpdateResponseDTO.builder()
                .userName(updatedBoard.getUser().getUserName())
                .title(updatedBoard.getTitle())
                .content(updatedBoard.getContent())
                .createDate(updatedBoard.getCreateDate())
                .updateDate(updatedBoard.getModifiedDate())
                .build();
    }
}

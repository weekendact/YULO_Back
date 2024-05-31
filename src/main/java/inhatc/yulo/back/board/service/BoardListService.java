package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.responsedto.BoardListResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true) // DB에게 읽기 전용이라는 것을 알려줌
public class BoardListService {

    private final BoardRepository boardRepository;

    public List<BoardListResponseDTO> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardListResponseDTO> responseDTOList = new ArrayList<>();

        for(Board board : boardList) {
            BoardListResponseDTO responseDTO = BoardListResponseDTO.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .userName(board.getUser().getUserName())
                    .build();
            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }
}

package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.responsedto.BoardListResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardSearchService {

    private final BoardRepository boardRepository;

    public List<BoardListResponseDTO> searchBoard(String title, String userName) {
        List<Board> boardList;

        // 검색
        // 제목을 검색한 경우
        if(title != null) {
            boardList = boardRepository.findByTitleContaining(title);
        } else if(userName != null) { // 이름으로 검색한 경우
            boardList = boardRepository.findByUser_UserNameContaining(userName);
        } else {
            boardList = boardRepository.findAll();
        }

        List<BoardListResponseDTO> responseDTOList = new ArrayList<>();
        for(Board board: boardList) {
            responseDTOList.add(fromEntity(board));
        }
        return responseDTOList;



    }

    // Entity -> DTO 변환
    private BoardListResponseDTO fromEntity(Board board) {
        return BoardListResponseDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .userName(board.getUser().getUserName())
                .build();
    }
}

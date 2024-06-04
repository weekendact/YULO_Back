package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.responsedto.BoardListResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true) // DB에게 읽기 전용이라는 것을 알려줌
public class BoardListService {

    private final BoardRepository boardRepository;

    public Page<BoardListResponseDTO> getBoardList(int page) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page - 1, pageSize); // 페이지 번호는 0부터 시작

        Page<Board> boardPage = boardRepository.findAll(pageable);
        List<BoardListResponseDTO> responseDTOList = new ArrayList<>();

        for (Board board : boardPage.getContent()) {
            String truncatedContent = truncateContent(board.getContent(), 15);
            BoardListResponseDTO responseDTO = BoardListResponseDTO.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .content(truncatedContent)
                    .userName(board.getUser().getUserName())
                    .createDate(board.getCreateDate())
                    .build();
            responseDTOList.add(responseDTO);
        }

        return new PageImpl<>(responseDTOList, pageable, boardPage.getTotalElements());
    }

    private String truncateContent(String content, int length) {
        if (content.length() > length) {
            return content.substring(0, length) + ".....";
        } else {
            return content;
        }
    }
}

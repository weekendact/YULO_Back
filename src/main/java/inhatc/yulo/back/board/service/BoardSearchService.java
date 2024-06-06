package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.responsedto.BoardListResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardSearchService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    public Page<BoardListResponseDTO> searchBoard(String title, String userName, int page) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page - 1, pageSize); // 페이지 번호는 0부터 시작

        Page<Board> boardPage;
        // 검색
        // 제목을 검색한 경우
        if(title != null) {
            boardPage = boardRepository.findByTitleContaining(title, pageable);
        } else if(userName != null) { // 이름으로 검색한 경우
            boardPage = boardRepository.findByUser_UserNameContaining(userName, pageable);
        } else {
            boardPage = boardRepository.findAll(pageable);
        }

        List<BoardListResponseDTO> responseDTOList = new ArrayList<>();
        for(Board board: boardPage.getContent()) {
            String truncatedContent = truncateContent(board.getContent(), 15);

            // 이미지 파일만 필터링
            List<File> files = fileRepository.findByBoardId(board.getId());
            List<String> imageUrls = new ArrayList<>();
            for (File file : files) {
                if (file.getOrigFilename().matches(".*\\.(jpg|jpeg|png|gif)$")) { // 이미지 파일만 필터링
                    imageUrls.add(file.getFilePath());
                }
            }
            BoardListResponseDTO responseDTO = BoardListResponseDTO.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .content(truncatedContent)
                    .userName(board.getUser().getUserName())
                    .createDate(board.getCreateDate())
                    .imageUrls(imageUrls)
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

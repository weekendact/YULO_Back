package inhatc.yulo.back.board.controller;

import inhatc.yulo.back.board.dto.requestdto.*;
import inhatc.yulo.back.board.dto.responsedto.*;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.service.*;
import inhatc.yulo.back.resultdto.ResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("board")
@CrossOrigin
@RequiredArgsConstructor
public class BoardController {

    private final BoardWriteService boardWriteService;
    private final BoardUpdateService boardUpdateService;
    private final BoardListService boardListService;
    private final BoardSearchService boardSearchService;
    private final BoardDeleteService boardDeleteService;
    private final HeartService heartService;
    private final BoardDetailService boardDetailService;
    private final CommentService commentService;

    // 게시글 쓰기
    @PostMapping("/write")
    public ResultDTO<?> writeBoard(@RequestBody BoardWriteRequestDTO boardWriteRequestDTO) {
        BoardWriteResponseDTO responseDTO = boardWriteService.writeBoard(boardWriteRequestDTO);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "Board created successfully", responseDTO, "data");
    }

    // 게시글 수정
    @PutMapping("/update")
    public ResultDTO<?> updateBoard(@RequestBody BoardUpdateRequestDTO boardUpdateRequestDTO) {
        try {
            BoardUpdateResponseDTO responseDTO = boardUpdateService.updateBoard(boardUpdateRequestDTO);
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Board updated successfully", responseDTO, "data");
        } catch (IllegalArgumentException e) {
            return new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "Board updated fail", null, "error");
        }
    }

    // 전체 리스트 보기
    @GetMapping("/list")
    public ResultDTO<?> getBoardList(@RequestParam(defaultValue = "1") int page) {
        Page<BoardListResponseDTO> boardPage = boardListService.getBoardList(page);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("content", boardPage.getContent());
//        responseData.put("totalElements", boardPage.getTotalElements()); // 전체 요소 수
//        responseData.put("totalPages", boardPage.getTotalPages()); // 전체 페이지 수
//        responseData.put("number", boardPage.getNumber()); // 현재 페이지 번호
//        responseData.put("size", boardPage.getSize()); // 페이지당 요소 수
//        responseData.put("first", boardPage.isFirst()); // 첫 번째 페이지인지 여부
//        responseData.put("last", boardPage.isLast()); // 마지막 페이지인지 여부

        return new ResultDTO<>().makeResult(HttpStatus.OK, "Board list retrieved successfully", responseData, "data");
    }

    // 제목으로 검색해서 리스트 보기
    @GetMapping("/searchByTitle")
    public ResultDTO<?> searchBoardTitle(@RequestParam String title) {
        List<BoardListResponseDTO> boardList = boardSearchService.searchBoard(title, null);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "Board title search successfully", boardList, "data");
    }

    // 작성자로 검색해서 리스트 보기
    @GetMapping("/searchByUser")
    public ResultDTO<?> searchBoardUserName(@RequestParam String userName) {
        List<BoardListResponseDTO> boardList = boardSearchService.searchBoard(null, userName);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "Board username search successfully", boardList, "data");
    }

    // 게시글 삭제
    @PostMapping("/delete")
    public ResultDTO<?> deleteBoard(@RequestBody BoardDeleteRequestDTO boardDeleteRequestDTO) {
        boolean deleted = boardDeleteService.deleteBoard(boardDeleteRequestDTO);
        if (deleted) {
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Board delete successfully", null, "data");
        } else {
            return new ResultDTO<>().makeResult(HttpStatus.NOT_FOUND, "Board not delete fail", null, "error");
        }
    }

    // 좋아요 추가
    @PostMapping("/heart")
    public ResultDTO<?> addHeart(@RequestBody HeartRequestDTO heartRequestDTO) {
        try {
            int heartCount = heartService.addHeart(heartRequestDTO);
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Heart added successfully", heartCount, "data");
        } catch (Exception e) {
            return new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "Heart added fail", null, "data");
        }
    }

    // 좋아요 삭제
    @PostMapping("/unheart")
    public ResultDTO<?> removeHeart(@RequestBody HeartRequestDTO heartRequestDTO) {
        try {
            int likeCount = heartService.removeHeart(heartRequestDTO);
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Heart removed successfully", likeCount, "data");
        } catch (Exception e) {
            return new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "Heart removed fail", null, "error");
        }
    }

    // 게시글 상세 조회
    @PostMapping("/detail")
    public ResultDTO<?> detailBoard(@RequestBody BoardDetailRequestDTO boardDetailRequestDTO) {
        try {
            BoardDetailResponseDTO boardDetail = boardDetailService.getBoardDetail(boardDetailRequestDTO.getBoardId());
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Board detail fetched successfully", boardDetail, "data");
        } catch (IllegalArgumentException e) {
            return new ResultDTO<>().makeResult(HttpStatus.NOT_FOUND, "Board Detail fail.", null, "error");
        }
    }

    // 댓글 추가
    @PostMapping("/comment")
    public ResultDTO<?> addComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        try {
            CommentResponseDTO commentResponseDTO = commentService.addComment(commentRequestDTO);
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Comment added successfully", commentResponseDTO, "data");
        } catch (IllegalArgumentException e) {
            return new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "Comment added fail", null, "error");
        }
    }

    // 댓글 삭제
    @PostMapping("/commentDelete")
    public ResultDTO<?> deleteComment(@RequestBody CommentDeleteRequestDTO commentDeleteRequestDTO) {
        boolean deleted = commentService.deleteComment(commentDeleteRequestDTO);
        if (deleted) {
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Comment deleted successfully", null, "data");
        } else {
            return new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "Comment delete fail", null, "error");
        }
    }

    @PostMapping("/commentUpdate")
    public ResultDTO<?> updateComment(@RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO) {
        try {
            CommentUpdateResponseDTO commentResponseDTO = commentService.updateComment(commentUpdateRequestDTO);
            return new ResultDTO<>().makeResult(HttpStatus.OK, "Comment updated successfully", commentResponseDTO, "data");
        } catch (IllegalArgumentException e) {
            return new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "Comment update fail", null, "error");
        }
    }
}

package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.responsedto.BoardDetailResponseDTO;
import inhatc.yulo.back.board.dto.responsedto.CommentResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.Comment;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.CommentRepository;
import inhatc.yulo.back.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardDetailService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;

    public BoardDetailResponseDTO getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        // 게시판에 있는 댓글
        List<Comment> commentEntities = commentRepository.findByBoard_Id(boardId);
        List<CommentResponseDTO> comments = new ArrayList<>();

        for(Comment comment: commentEntities) {
            CommentResponseDTO commentDTO = CommentResponseDTO.builder()
                    .id(comment.getId())
                    .userName(comment.getUser().getUserName())
                    .content(comment.getContent())
                    .createDate(comment.getCreateDate())
                    .build();
            comments.add(commentDTO);
        }

        // 게시판에 있는 파일
        List<File> files = fileRepository.findByBoardId(boardId);

        return BoardDetailResponseDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .userName(board.getUser().getUserName())
                .createDate(board.getCreateDate())
                .modifiedDate(board.getModifiedDate())
                .heartCount(board.getHeartCount())
                .comments(comments)
                .files(files)
                .build();


    }
}

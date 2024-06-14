package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.CommentDeleteRequestDTO;
import inhatc.yulo.back.board.dto.requestdto.CommentRequestDTO;
import inhatc.yulo.back.board.dto.requestdto.CommentUpdateRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.CommentResponseDTO;
import inhatc.yulo.back.board.dto.responsedto.CommentUpdateResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.Comment;
import inhatc.yulo.back.board.entity.Notice;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.CommentRepository;
import inhatc.yulo.back.board.repository.NoticeRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    // 댓글 추가
    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO) {

        User user = userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comment comment = Comment.builder()
                .user(user)
                .content(commentRequestDTO.getContent())
                .build();

        // 게시판 댓글 작성
        if(commentRequestDTO.getBoardId() != null) {
            Board board = boardRepository.findById(commentRequestDTO.getBoardId())
                    .orElseThrow( () -> new IllegalArgumentException("Board not found"));
            comment.setBoard(board);
        }

        // 공지사항 댓글 작성
        if(commentRequestDTO.getNoticeId() != null) {
            Notice notice = noticeRepository.findById(commentRequestDTO.getNoticeId())
                    .orElseThrow( () -> new IllegalArgumentException("Notice not found"));
            comment.setNotice(notice);
        }

        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDTO.builder()
                .id(savedComment.getId())
                .userName(savedComment.getUser().getUserName())
                .content(savedComment.getContent())
                .createDate(savedComment.getCreateDate())
                .build();
    }

    // 댓글 삭제
    public boolean deleteComment(CommentDeleteRequestDTO commentDeleteRequestDTO) {
        User user = userRepository.findById(commentDeleteRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comment comment = commentRepository.findById(commentDeleteRequestDTO.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        // 게시판 댓글 삭제
        if(commentDeleteRequestDTO.getBoardId() != null) {
            Board board = boardRepository.findById(commentDeleteRequestDTO.getBoardId())
                    .orElseThrow( () -> new IllegalArgumentException("Board not found"));
            if(comment.getBoard() == null || !comment.getBoard().getId().equals(board.getId())) {
                throw new IllegalArgumentException("Comment does not belong to the given board");
            }
        }

        // 공지사항 댓글 삭제
        if (commentDeleteRequestDTO.getNoticeId() != null) {
            Notice notice = noticeRepository.findById(commentDeleteRequestDTO.getNoticeId())
                    .orElseThrow(() -> new IllegalArgumentException("Notice not found"));
            if (comment.getNotice() == null || !comment.getNotice().getId().equals(notice.getId())) {
                throw new IllegalArgumentException("Comment does not belong to the given notice");
            }
        }

        // 관리자(id가 2) + 작성자 본인일 경우에만 삭제가 가능
        if (comment.getUser().getUserId().equals(user.getUserId()) || user.getUserId().equals(2L)) {
            commentRepository.delete(comment);
            return true;
        } else {
            return false;
        }
    }

    // 댓글 수정
    public CommentUpdateResponseDTO updateComment(CommentUpdateRequestDTO commentUpdateRequestDTO) {
        User user = userRepository.findById(commentUpdateRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comment comment = commentRepository.findById(commentUpdateRequestDTO.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        // 게시판 댓글 수정
        if (commentUpdateRequestDTO.getBoardId() != null) {
            Board board = boardRepository.findById(commentUpdateRequestDTO.getBoardId())
                    .orElseThrow(() -> new IllegalArgumentException("Board not found"));
            if (comment.getBoard() == null || !comment.getBoard().getId().equals(board.getId())) {
                throw new IllegalArgumentException("Comment does not belong to the given board");
            }
        }

        // 공지사항 댓글 수정
        if (commentUpdateRequestDTO.getNoticeId() != null) {
            Notice notice = noticeRepository.findById(commentUpdateRequestDTO.getNoticeId())
                    .orElseThrow(() -> new IllegalArgumentException("Notice not found"));
            if (comment.getNotice() == null || !comment.getNotice().getId().equals(notice.getId())) {
                throw new IllegalArgumentException("Comment does not belong to the given notice");
            }
        }

        if (!comment.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("Only the comment author can update the comment");
        }

        comment.setContent(commentUpdateRequestDTO.getContent());
        commentRepository.save(comment);

        return CommentUpdateResponseDTO.builder()
                .id(comment.getId())
                .userName(comment.getUser().getUserName())
                .content(comment.getContent())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }
}

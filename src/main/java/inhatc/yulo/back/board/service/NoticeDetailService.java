package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.responsedto.CommentResponseDTO;
import inhatc.yulo.back.board.dto.responsedto.NoticeDetailResponseDTO;
import inhatc.yulo.back.board.entity.Comment;
import inhatc.yulo.back.board.entity.Notice;
import inhatc.yulo.back.board.repository.CommentRepository;
import inhatc.yulo.back.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeDetailService {

    private final NoticeRepository noticeRepository;
    private final CommentRepository commentRepository;

    public NoticeDetailResponseDTO getNoticeDetail(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow( () -> new IllegalArgumentException("Notice not found"));

        // 공지사항에 있는 댓글
        List<Comment> commentEntities = commentRepository.findByNotice_Id(noticeId);
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

        return NoticeDetailResponseDTO.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .userName(notice.getUser().getUserName())
                .createDate(notice.getCreateDate())
                .modifiedDate(notice.getModifiedDate())
                .comments(comments)
                .build();
    }


}

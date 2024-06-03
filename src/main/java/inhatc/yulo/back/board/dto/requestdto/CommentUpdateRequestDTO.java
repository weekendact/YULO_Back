package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateRequestDTO {

    private Long boardId;

    private Long noticeId;

    private Long userId;

    private Long commentId;

    private String content;
}

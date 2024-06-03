package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDTO {

    private Long boardId;

    private Long noticeId;

    private Long userId;

    private String content;
}

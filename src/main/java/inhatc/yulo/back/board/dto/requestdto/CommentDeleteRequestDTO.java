package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDeleteRequestDTO {

    private Long userId;

    private Long boardId;

    private Long commentId;
}

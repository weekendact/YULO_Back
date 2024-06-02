package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeUpdateRequestDTO {

    private Long userId;

    private Long noticeId; // 수정할 게시물의 id

    private String title;

    private String content;
}

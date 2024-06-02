package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDeleteRequestDTO {

    private Long noticeId;

    private Long userId;
}

package inhatc.yulo.back.board.dto.responsedto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeListResponseDTO {

    private Long noticeId;

    private String userName;

    private String title;

}

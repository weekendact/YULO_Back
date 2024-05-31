package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardWriteRequestDTO {

    private Long userId;

    private String title; // 제목

    private String content; // 내용


}

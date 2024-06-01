package inhatc.yulo.back.board.dto.responsedto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListResponseDTO {

    private Long boardId; // 게시글 번호

    private String title; // 제목

    private String userName; // 작성자 이름

}

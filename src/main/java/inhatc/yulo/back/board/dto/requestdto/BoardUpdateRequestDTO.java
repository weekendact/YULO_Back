package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUpdateRequestDTO {

    private Long boardId; // 수정할 게시물의 ID

    private Long userId; // 요청하는 사용자의 ID

    private String title; // 제목

    private String content; // 내용

    private MultipartFile[] newFiles; // 새로 추가할 파일

    private Long[] deleteFileId; // 삭제할 파일 ID
}

package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile[] newFiles; // 새로 추가할 파일들

    private Long[] deleteFileId; // 삭제할 파일들 ID
}

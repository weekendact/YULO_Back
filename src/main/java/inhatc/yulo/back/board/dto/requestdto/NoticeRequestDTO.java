package inhatc.yulo.back.board.dto.requestdto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeRequestDTO {

    private Long userId;

    private String title;

    private String content;

    private MultipartFile[] files;
}

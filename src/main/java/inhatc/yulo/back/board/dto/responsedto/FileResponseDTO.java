package inhatc.yulo.back.board.dto.responsedto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponseDTO {

    private Long fileId;
    private String fileName;
}

package inhatc.yulo.back.graph.dto.responseDTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GraphListResponseDTO {
    private String name;
    private List<Integer> data;
    private List<LocalDateTime> dateTime;
}

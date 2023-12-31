package Patient.Dtos;

import lombok.Data;
import java.util.List;
@Data
public class PatientResponse {
    private List<PatientDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}

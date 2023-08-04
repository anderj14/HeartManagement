package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiseaseHistoryDto {
    private int id;
    private LocalDate startDate;
    private String description;
    private String treatment;
}

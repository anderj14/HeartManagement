package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiagnosticDto {
    private int id;
    private LocalDate date;
    private String conditionName;
    private String description;
    private String classificationCondition;
    private String severity;
    private String RiskAssessment;
    private String conclusions;
}

package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreatmentDto {
    private int id;
    private LocalDate date;
    private String medication;
    private String dosage;
    private String instructions;
    private String otherTreatments;
    private String sideEffects;
    private String treatmentMonitoring;
}

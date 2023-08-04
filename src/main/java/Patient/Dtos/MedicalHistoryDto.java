package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalHistoryDto {
    private int id;
    private LocalDate date;
    private String previousHeartDisease;
    private String highBloodPressure;
    private String diabetes;
    private String hyperlipidemia;
    private String obesity;
    private String smoking;
    private String cardiacProceduresSurgeries;
    private String systemicDiseases;
    private String medications;
    private String familyDiseases;
}

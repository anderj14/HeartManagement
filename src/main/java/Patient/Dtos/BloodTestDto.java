package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BloodTestDto {
    private int id;
    private LocalDate date;
    private String hemoglobin;
    private String hematocrit;
    private String whiteBloodCell;
    private String platelets;
    private String glucose;
    private String cholesterolHDL;
    private String cholesterolLDL;
    private String triglycerides;
}

package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PhysicalExaminationDto {
    private int id;
    private LocalDate date;
    private String bloodPressure;
    private String heartRate;
    private String heartRhythm;
    private String cardiacAuscultation;
    private String lungAuscultation;
    private String PeripheralEdema;
    private String Palpitations;
    private String GeneralVitalSigns;
}

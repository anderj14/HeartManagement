package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ElectrocardiogramDto {
    private int id;
    private LocalDate date;
    private String heartRhythm;
    private String intervalsSegments;
    private String characteristicWaves;
    private String heartRate;
    private String abnormalities;
    private String artifacts;
}

package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class StressTestDto {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String duration;
    private String maxHeartRate;
    private String peakPressure;
    private String exerciseInducedSymptoms;
    private String abnormalEcgFindings;
    private String imageEco;
    private String imageStress;
    private String Conclusion;
}

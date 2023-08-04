package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class HolterStudyDto {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String studyDuration;
    private String averageHeartRate;
    private String maximumHeartRate;
    private String typeHeartRhythm;
    private String arrhythmiaEpisodes;
    private String physicalActivity;
    private String patientSymptoms;
    private String conclusion;

}

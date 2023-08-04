package Patient.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhysicalExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

}

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
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}

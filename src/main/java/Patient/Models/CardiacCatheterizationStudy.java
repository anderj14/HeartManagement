package Patient.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardiacCatheterizationStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String numLocationMainCoronary;
    private String blockageEachCoronaryArtery;
    private String descriptionAbnormality;
    private String bloodPressureAorta;
    private String chambersLeftAtrium;
    private String chambersLeftVentricle;
    private String chambersRightAtrium;
    private String chambersRightVentricle;
    private String bloodFlowCoronaryArteries;
    private String velocityBloodFlow;
    private String leftVentricularEjectionFraction;
    private String bloodPressurePulmonaryArteries;
    private String valvularInsufficiencyAortic;
    private String valvularInsufficiencyMitral;
    private String valvularInsufficiencyPulmonary;
    private String valvularInsufficiencyTricuspid;
    private String pressureGradientValves;
    private String structuralAbnormalities;
    private String functionsCardiacChambers;
    private String descriptionComplication;
    private String conclusion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

}

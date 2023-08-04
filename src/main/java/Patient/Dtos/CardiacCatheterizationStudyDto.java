package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CardiacCatheterizationStudyDto {

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
}

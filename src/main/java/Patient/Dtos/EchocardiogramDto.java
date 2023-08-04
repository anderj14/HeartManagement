package Patient.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EchocardiogramDto {
    private int id;
    private LocalDate date;
    private String cardiacDimensions;
    private String ejectionFraction;
    private String valveFunction;
    private String velocitiesBloodFlows;
    private String movementCardiacWalls;
    private String pulmonaryArterialPressure;
    private String bloodFlow;
}

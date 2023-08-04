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
public class Echocardiogram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private String cardiacDimensions;
    private String ejectionFraction;
    private String valveFunction;
    private String velocitiesBloodFlows;
    private String movementCardiacWalls;
    private String pulmonaryArterialPressure;
    private String bloodFlow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}

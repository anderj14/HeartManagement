package Patient.Repository;

import Patient.Models.Electrocardiogram;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ElectrocardiogramRepository extends JpaRepository<Electrocardiogram, Integer> {
    List<Electrocardiogram> findByPatientId(int patientId);
}

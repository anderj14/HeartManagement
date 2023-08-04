package Patient.Repository;

import Patient.Models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    List<Treatment> findByPatientId(int patientId);
}

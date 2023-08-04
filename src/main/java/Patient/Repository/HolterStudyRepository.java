package Patient.Repository;

import Patient.Models.HolterStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HolterStudyRepository extends JpaRepository<HolterStudy, Integer> {
    List<HolterStudy> findByPatientId(int patientId);
}

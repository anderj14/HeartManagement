package Patient.Repository;

import Patient.Models.CardiacCatheterizationStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CardiacCatheterizationStudyRepository extends JpaRepository<CardiacCatheterizationStudy, Integer> {
    List<CardiacCatheterizationStudy> findByPatientId(int patientId);
}

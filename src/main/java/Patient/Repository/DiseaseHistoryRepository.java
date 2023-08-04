package Patient.Repository;

import Patient.Models.DiseaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseHistoryRepository extends JpaRepository<DiseaseHistory, Integer> {
    List<DiseaseHistory> findByPatientId(int patientId);

}

package Patient.Repository;

import Patient.Models.StressTest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StressTestRepository extends JpaRepository<StressTest, Integer> {

    List<StressTest> findByPatientId(int patientId);
}

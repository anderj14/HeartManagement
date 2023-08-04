package Patient.Repository;

import Patient.Models.BloodTest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BloodTestRepository extends JpaRepository<BloodTest, Integer> {
    List<BloodTest> findByPatientId(int patientId);
}

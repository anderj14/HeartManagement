package Patient.Repository;

import Patient.Models.PhysicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhysicalExaminationRepository extends JpaRepository<PhysicalExamination, Integer> {
    List<PhysicalExamination> findByPatientId(int patientId);
}

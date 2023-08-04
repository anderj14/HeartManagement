package Patient.Repository;

import Patient.Models.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Integer> {
    List<Diagnostic> findByPatientId(int patientId);
}

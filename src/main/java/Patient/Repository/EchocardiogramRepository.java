package Patient.Repository;

import Patient.Models.Echocardiogram;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EchocardiogramRepository extends JpaRepository<Echocardiogram, Integer> {

    List<Echocardiogram> findByPatientId(int patientId);
}

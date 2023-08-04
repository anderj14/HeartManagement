package Patient.Repository;

import Patient.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatientId(int patientId);
//    Optional<Appointment> findByIdAndPatientId(int patientId, int appointmentId);
}

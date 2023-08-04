package Patient.Service;

import Patient.Dtos.AppointmentDto;
import java.util.List;

public interface AppointmentService {
    AppointmentDto createAppointment(int patientId, AppointmentDto appointmentDto);
    List<AppointmentDto> getAppointmentByPatientId(int id);
    List<AppointmentDto> appointmentById(int patientId, int appointmentId);
    AppointmentDto updateAppointment(int patientId, int appointmentId, AppointmentDto appointmentDto);
    void deleteAppointment(int patientId, int appointmentId);
}

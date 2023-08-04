package Patient.Service;

import Patient.Dtos.TreatmentDto;
import java.util.List;

public interface TreatmentService {
    TreatmentDto createTreatment(int patientId, TreatmentDto treatmentDto);
    List<TreatmentDto> getTreatmentByPatientId(int id);
    TreatmentDto TreatmentById(int patientId, int treatmentId);
    TreatmentDto updateTreatment(int patientId, int treatmentId, TreatmentDto treatmentDto);
    void deleteTreatment(int patientId, int treatmentId);

}

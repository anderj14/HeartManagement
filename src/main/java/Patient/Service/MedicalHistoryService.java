package Patient.Service;

import Patient.Dtos.MedicalHistoryDto;
import java.util.List;

public interface MedicalHistoryService {
    MedicalHistoryDto createMedicalHistory(int patientId, MedicalHistoryDto medicalHistoryDto);
    List<MedicalHistoryDto> getMedicalHistoriesByPatientId(int id);
    MedicalHistoryDto medicalHistoryById(int patientId, int medicalHistoryId);
    MedicalHistoryDto updateMedicalHistory(int patientId, int medicalHistoryId, MedicalHistoryDto medicalHistoryDto);
    void deleteMedicalHistory(int patientId, int medicalHistoryId);
}

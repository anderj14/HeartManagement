package Patient.Service;

import Patient.Dtos.DiseaseHistoryDto;
import java.util.List;

public interface DiseaseHistoryService {
    DiseaseHistoryDto createDiseaseHist(int patientId, DiseaseHistoryDto diseaseHistoryDto);
    List<DiseaseHistoryDto> getDiseaseHistByPatientId(int id);
    DiseaseHistoryDto diseaseHistById(int patientId, int diseaseHistId);
    DiseaseHistoryDto updateDiseaseHist(int patientId, int diseaseHistId, DiseaseHistoryDto diseaseHistoryDto);
    void deleteDisease(int patientId, int diseaseHistId);
}

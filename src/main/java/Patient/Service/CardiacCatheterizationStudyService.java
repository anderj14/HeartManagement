package Patient.Service;

import Patient.Dtos.CardiacCatheterizationStudyDto;
import java.util.List;

public interface CardiacCatheterizationStudyService {
    CardiacCatheterizationStudyDto createCardiacCatheterizationStudy(
            int patientId,
            CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto
            );
    List<CardiacCatheterizationStudyDto> getCardiacCatheterizationStudiesByPatientId(int id);
    CardiacCatheterizationStudyDto CardiacCatheterizationStudyById(
            int patientId,
            int cardiacCatheterizationStudyId
    );
    CardiacCatheterizationStudyDto updateCardiacCatheterizationStudyById(
            int patientId,
            int cardiacCatheterizationStudyId,
            CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto
    );
    void deleteCardiacCatheterizationStudyById(
            int patientId,
            int cardiacCatheterizationStudyId
    );
}

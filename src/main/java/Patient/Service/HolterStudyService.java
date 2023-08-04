package Patient.Service;

import Patient.Dtos.HolterStudyDto;
import java.util.List;

public interface HolterStudyService {
    HolterStudyDto createHolterStudy(int patientId, HolterStudyDto holterStudyDto);
    List<HolterStudyDto> getHolterStudiesByPatientId(int id);
    HolterStudyDto holterStudyById(int patientId, int holterStudyId);
    HolterStudyDto updateHolterStudy(int patientId, int holterStudyId, HolterStudyDto holterStudyDto);
    void deleteHolterStudy(int patientId, int holterStudyId);
}

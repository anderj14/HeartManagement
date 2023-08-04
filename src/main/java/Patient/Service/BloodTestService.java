package Patient.Service;

import Patient.Dtos.BloodTestDto;
import java.util.List;

public interface BloodTestService {
    BloodTestDto createBloodTest(int patientId, BloodTestDto bloodTestDto);
    List<BloodTestDto> getBloodTestsByPatientId(int id);
    BloodTestDto bloodTestById(int patientId, int bloodTestId);
    BloodTestDto updateBloodTestDto(int patientId, int bloodTestId, BloodTestDto bloodTestDto);
    void deleteBloodTest(int patientId, int bloodTestId);
}

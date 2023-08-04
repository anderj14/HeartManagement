package Patient.Service;

import Patient.Dtos.StressTestDto;

import java.util.List;
public interface StressTestService {
    StressTestDto createStressTest(int patientId, StressTestDto stressTestDto);
    List<StressTestDto> getStressTestsByPatientId(int id);
    StressTestDto stressTestId(int patientId, int stressTestId);
    StressTestDto updateStressTest(int patientId, int stressTestId, StressTestDto stressTestDto);
    void deleteStressTest(int patientId, int stressTestId);
}

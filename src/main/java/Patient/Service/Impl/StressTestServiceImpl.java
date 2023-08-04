package Patient.Service.Impl;

import Patient.Dtos.StressTestDto;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Exceptions.StressTestNotFoundException;
import Patient.Models.Patient;
import Patient.Models.StressTest;
import Patient.Repository.PatientRepository;
import Patient.Repository.StressTestRepository;
import Patient.Service.StressTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StressTestServiceImpl implements StressTestService {
    private StressTestRepository stressTestRepository;
    private PatientRepository patientRepository;

    @Autowired
    public StressTestServiceImpl(StressTestRepository stressTestRepository, PatientRepository patientRepository) {
        this.stressTestRepository = stressTestRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public StressTestDto createStressTest(int patientId, StressTestDto stressTestDto) {
        StressTest stressTest = mapToEntity(stressTestDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated stress test not found"));

        stressTest.setPatient(patient);
        StressTest newStressTest = stressTestRepository.save(stressTest);
        return mapToDto(newStressTest);
    }

    @Override
    public List<StressTestDto> getStressTestsByPatientId(int id) {
        List<StressTest> stressTests = stressTestRepository.findByPatientId(id);

        return stressTests.stream().map(st -> mapToDto(st)).collect(Collectors.toList());
    }

    @Override
    public StressTestDto stressTestId(int patientId, int stressTestId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        List<StressTest> stressTests = patient.getStressTests();
        StressTest stressTest = stressTests.stream()
                .filter(st -> st.getId() == stressTestId)
                .findFirst()
                .orElseThrow(() -> new StressTestNotFoundException("Stress test not found"));

        return mapToDto(stressTest);
    }

    @Override
    public StressTestDto updateStressTest(int patientId, int stressTestId, StressTestDto stressTestDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        StressTest stressTest = stressTestRepository.findById(stressTestId).orElseThrow(() ->
                new StressTestNotFoundException("Stress test not found"));

        if(stressTest.getPatient().getId() != patient.getId()){
            throw new StressTestNotFoundException("This stress test does not belong to a patient");
        }

        stressTest.setDate(stressTestDto.getDate());
        stressTest.setTime(stressTestDto.getTime());
        stressTest.setDuration(stressTestDto.getDuration());
        stressTest.setMaxHeartRate(stressTestDto.getMaxHeartRate());
        stressTest.setPeakPressure(stressTestDto.getPeakPressure());
        stressTest.setExerciseInducedSymptoms(stressTestDto.getExerciseInducedSymptoms());
        stressTest.setAbnormalEcgFindings(stressTestDto.getAbnormalEcgFindings());
        stressTest.setImageEco(stressTestDto.getImageEco());
        stressTest.setImageStress(stressTestDto.getImageStress());
        stressTest.setConclusion(stressTestDto.getConclusion());

        StressTest updateStressTest = stressTestRepository.save(stressTest);
        return mapToDto(updateStressTest);
    }

    @Override
    public void deleteStressTest(int patientId, int stressTestId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        StressTest stressTest = stressTestRepository.findById(stressTestId).orElseThrow(() ->
                new StressTestNotFoundException("Stress test not found"));

        if(stressTest.getPatient().getId() != patient.getId()){
            throw new StressTestNotFoundException("This stress test does not belong to a patient");
        }

        stressTestRepository.delete(stressTest);
    }

    private StressTestDto mapToDto(StressTest stressTest){
        StressTestDto stressTestDto = new StressTestDto();
        stressTestDto.setId(stressTest.getId());
        stressTestDto.setDate(stressTest.getDate());
        stressTestDto.setTime(stressTest.getTime());
        stressTestDto.setDuration(stressTest.getDuration());
        stressTestDto.setMaxHeartRate(stressTest.getMaxHeartRate());
        stressTestDto.setPeakPressure(stressTest.getPeakPressure());
        stressTestDto.setExerciseInducedSymptoms(stressTest.getExerciseInducedSymptoms());
        stressTestDto.setAbnormalEcgFindings(stressTest.getAbnormalEcgFindings());
        stressTestDto.setImageEco(stressTest.getImageEco());
        stressTestDto.setImageStress(stressTest.getImageStress());
        stressTestDto.setConclusion(stressTest.getConclusion());

        return stressTestDto;
    }

    private StressTest mapToEntity(StressTestDto stressTestDto){
        StressTest stressTest = new StressTest();
        stressTest.setId(stressTestDto.getId());
        stressTest.setDate(stressTestDto.getDate());
        stressTest.setTime(stressTestDto.getTime());
        stressTest.setDuration(stressTestDto.getDuration());
        stressTest.setMaxHeartRate(stressTestDto.getMaxHeartRate());
        stressTest.setPeakPressure(stressTestDto.getPeakPressure());
        stressTest.setExerciseInducedSymptoms(stressTestDto.getExerciseInducedSymptoms());
        stressTest.setAbnormalEcgFindings(stressTestDto.getAbnormalEcgFindings());
        stressTest.setImageEco(stressTestDto.getImageEco());
        stressTest.setImageStress(stressTestDto.getImageStress());
        stressTest.setConclusion(stressTestDto.getConclusion());

        return stressTest;
    }
}

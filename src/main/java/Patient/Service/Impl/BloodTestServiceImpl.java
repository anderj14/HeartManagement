package Patient.Service.Impl;

import Patient.Dtos.BloodTestDto;
import Patient.Exceptions.BloodTestNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.BloodTest;
import Patient.Models.Patient;
import Patient.Repository.BloodTestRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.BloodTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloodTestServiceImpl implements BloodTestService {
    private BloodTestRepository bloodTestRepository;
    private PatientRepository patientRepository;

    @Autowired
    public BloodTestServiceImpl(BloodTestRepository bloodTestRepository, PatientRepository patientRepository) {
        this.bloodTestRepository = bloodTestRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public BloodTestDto createBloodTest(int patientId, BloodTestDto bloodTestDto) {
        BloodTest bloodTest = mapToEntity(bloodTestDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated echocardiogram not found"));

        bloodTest.setPatient(patient);
        BloodTest updateBloodTest = bloodTestRepository.save(bloodTest);
        return mapToDto(updateBloodTest);
    }

    @Override
    public List<BloodTestDto> getBloodTestsByPatientId(int id) {
        List<BloodTest> bloodTests = bloodTestRepository.findByPatientId(id);
        return bloodTests.stream().map(bt -> mapToDto(bt)).collect(Collectors.toList());
    }

    @Override
    public BloodTestDto bloodTestById(int patientId, int bloodTestId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("patient not found"));

        List<BloodTest> bloodTests = patient.getBloodTests();
        BloodTest bloodTest = bloodTests.stream()
                .filter(bt -> bt.getId() == bloodTestId)
                .findFirst()
                .orElseThrow(() -> new BloodTestNotFoundException("Blood test not found"));

        return mapToDto(bloodTest);
    }

    @Override
    public BloodTestDto updateBloodTestDto(int patientId, int bloodTestId, BloodTestDto bloodTestDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));
        BloodTest bloodTest = bloodTestRepository.findById(bloodTestId).orElseThrow(() ->
                new BloodTestNotFoundException("blood test not found"));

        if(bloodTest.getPatient().getId() != patient.getId()){
            throw new BloodTestNotFoundException("This  blood test does  not belong to a patient");
        }

        bloodTest.setDate(bloodTestDto.getDate());
        bloodTest.setHemoglobin(bloodTestDto.getHemoglobin());
        bloodTest.setHematocrit(bloodTestDto.getHematocrit());
        bloodTest.setWhiteBloodCell(bloodTestDto.getWhiteBloodCell());
        bloodTest.setPlatelets(bloodTestDto.getPlatelets());
        bloodTest.setGlucose(bloodTestDto.getGlucose());
        bloodTest.setCholesterolHDL(bloodTestDto.getCholesterolHDL());
        bloodTest.setCholesterolLDL(bloodTestDto.getCholesterolLDL());
        bloodTest.setTriglycerides(bloodTestDto.getTriglycerides());

        BloodTest updateBloodTest = bloodTestRepository.save(bloodTest);
        return mapToDto(updateBloodTest);
    }

    @Override
    public void deleteBloodTest(int patientId, int bloodTestId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));
        BloodTest bloodTest = bloodTestRepository.findById(bloodTestId).orElseThrow(() ->
                new BloodTestNotFoundException("Blood test not found"));

        if(bloodTest.getPatient().getId() != patient.getId()){
            throw new BloodTestNotFoundException("This blood test not belong to a patient");
        }

        bloodTestRepository.delete(bloodTest);
    }

    private BloodTestDto mapToDto(BloodTest bloodTest){
        BloodTestDto bloodTestDto = new BloodTestDto();
        bloodTestDto.setId(bloodTest.getId());
        bloodTestDto.setDate(bloodTest.getDate());
        bloodTestDto.setHemoglobin(bloodTest.getHemoglobin());
        bloodTestDto.setHematocrit(bloodTest.getHematocrit());
        bloodTestDto.setWhiteBloodCell(bloodTest.getWhiteBloodCell());
        bloodTestDto.setPlatelets(bloodTest.getPlatelets());
        bloodTestDto.setGlucose(bloodTest.getGlucose());
        bloodTestDto.setCholesterolHDL(bloodTest.getCholesterolHDL());
        bloodTestDto.setCholesterolLDL(bloodTest.getCholesterolLDL());
        bloodTestDto.setTriglycerides(bloodTest.getTriglycerides());

        return bloodTestDto;
    }

    private BloodTest mapToEntity(BloodTestDto bloodTestDto){
        BloodTest bloodTest = new BloodTest();
        bloodTest.setId(bloodTestDto.getId());
        bloodTest.setDate(bloodTestDto.getDate());
        bloodTest.setHemoglobin(bloodTestDto.getHemoglobin());
        bloodTest.setHematocrit(bloodTestDto.getHematocrit());
        bloodTest.setWhiteBloodCell(bloodTestDto.getWhiteBloodCell());
        bloodTest.setPlatelets(bloodTestDto.getPlatelets());
        bloodTest.setGlucose(bloodTestDto.getGlucose());
        bloodTest.setCholesterolHDL(bloodTestDto.getCholesterolHDL());
        bloodTest.setCholesterolLDL(bloodTestDto.getCholesterolLDL());
        bloodTest.setTriglycerides(bloodTestDto.getTriglycerides());

        return bloodTest;
    }
}

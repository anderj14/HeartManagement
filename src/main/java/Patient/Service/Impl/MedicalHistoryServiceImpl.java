package Patient.Service.Impl;

import Patient.Dtos.MedicalHistoryDto;
import Patient.Exceptions.MedicalHistoryNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.MedicalHistory;
import Patient.Models.Patient;
import Patient.Repository.MedicalHistoryRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private MedicalHistoryRepository medicalHistoryRepository;
    private PatientRepository patientRepository;

    @Autowired
    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, PatientRepository patientRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public MedicalHistoryDto createMedicalHistory(int patientId, MedicalHistoryDto medicalHistoryDto) {
        MedicalHistory medicalHistory = mapToEntity(medicalHistoryDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated medical history not found"));

        medicalHistory.setPatient(patient);
        MedicalHistory newMedicalHistory = medicalHistoryRepository.save(medicalHistory);
        return mapToDto(newMedicalHistory);
    }

    @Override
    public List<MedicalHistoryDto> getMedicalHistoriesByPatientId(int id) {
        List<MedicalHistory> medicalHistories = medicalHistoryRepository.findByPatientId(id);

        return medicalHistories.stream().map((mh) -> mapToDto(mh)).collect(Collectors.toList());
    }

    @Override
    public MedicalHistoryDto medicalHistoryById(int patientId, int medicalHistoryId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found!!!"));

        List<MedicalHistory> medicalHistories = patient.getMedicalHistories();

        MedicalHistory medicalHistory = medicalHistories.stream()
                .filter(mh -> mh.getId() == medicalHistoryId)
                .findFirst()
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history not found"));

        return mapToDto(medicalHistory);
    }

    @Override
    public MedicalHistoryDto updateMedicalHistory(int patientId, int medicalHistoryId, MedicalHistoryDto medicalHistoryDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        MedicalHistory medicalHistory = medicalHistoryRepository.findById(medicalHistoryId).orElseThrow(() ->
                new MedicalHistoryNotFoundException("Medical history not found"));

        if(medicalHistory.getPatient().getId() != patient.getId()){
            throw new MedicalHistoryNotFoundException("This medical history does not belong to a patient");
        }

        medicalHistory.setDate(medicalHistoryDto.getDate());
        medicalHistory.setPreviousHeartDisease(medicalHistoryDto.getPreviousHeartDisease());
        medicalHistory.setHighBloodPressure(medicalHistoryDto.getHighBloodPressure());
        medicalHistory.setDiabetes(medicalHistoryDto.getDiabetes());
        medicalHistory.setHyperlipidemia(medicalHistoryDto.getHyperlipidemia());
        medicalHistory.setObesity(medicalHistoryDto.getObesity());
        medicalHistory.setSmoking(medicalHistoryDto.getSmoking());
        medicalHistory.setCardiacProceduresSurgeries(medicalHistoryDto.getCardiacProceduresSurgeries());
        medicalHistory.setSystemicDiseases(medicalHistoryDto.getSystemicDiseases());
        medicalHistory.setMedications(medicalHistoryDto.getMedications());
        medicalHistory.setFamilyDiseases(medicalHistoryDto.getFamilyDiseases());

        MedicalHistory updateMedicalHistory = medicalHistoryRepository.save(medicalHistory);
        return mapToDto(updateMedicalHistory);
    }

    @Override
    public void deleteMedicalHistory(int patientId, int medicalHistoryId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        MedicalHistory medicalHistory = medicalHistoryRepository.findById(medicalHistoryId).orElseThrow(() ->
                new MedicalHistoryNotFoundException("Medical history not found"));

        if(medicalHistory.getPatient().getId() != patient.getId()){
            throw new MedicalHistoryNotFoundException("This medical history does not belong to a patient");
        }

        medicalHistoryRepository.delete(medicalHistory);
    }

    private MedicalHistoryDto mapToDto(MedicalHistory medicalHistory){
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
        medicalHistoryDto.setId(medicalHistory.getId());
        medicalHistoryDto.setDate(medicalHistory.getDate());
        medicalHistoryDto.setPreviousHeartDisease(medicalHistory.getPreviousHeartDisease());
        medicalHistoryDto.setHighBloodPressure(medicalHistory.getHighBloodPressure());
        medicalHistoryDto.setDiabetes(medicalHistory.getDiabetes());
        medicalHistoryDto.setHyperlipidemia(medicalHistory.getHyperlipidemia());
        medicalHistoryDto.setObesity(medicalHistory.getObesity());
        medicalHistoryDto.setSmoking(medicalHistory.getSmoking());
        medicalHistoryDto.setCardiacProceduresSurgeries(medicalHistory.getCardiacProceduresSurgeries());
        medicalHistoryDto.setSystemicDiseases(medicalHistory.getSystemicDiseases());
        medicalHistoryDto.setMedications(medicalHistory.getMedications());
        medicalHistoryDto.setFamilyDiseases(medicalHistory.getFamilyDiseases());

        return medicalHistoryDto;
    }

    private MedicalHistory mapToEntity(MedicalHistoryDto medicalHistoryDto){
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setId(medicalHistoryDto.getId());
        medicalHistory.setDate(medicalHistoryDto.getDate());
        medicalHistory.setPreviousHeartDisease(medicalHistoryDto.getPreviousHeartDisease());
        medicalHistory.setHighBloodPressure(medicalHistoryDto.getHighBloodPressure());
        medicalHistory.setDiabetes(medicalHistoryDto.getDiabetes());
        medicalHistory.setHyperlipidemia(medicalHistoryDto.getHyperlipidemia());
        medicalHistory.setObesity(medicalHistoryDto.getObesity());
        medicalHistory.setSmoking(medicalHistoryDto.getSmoking());
        medicalHistory.setCardiacProceduresSurgeries(medicalHistoryDto.getCardiacProceduresSurgeries());
        medicalHistory.setSystemicDiseases(medicalHistoryDto.getSystemicDiseases());
        medicalHistory.setMedications(medicalHistoryDto.getMedications());
        medicalHistory.setFamilyDiseases(medicalHistoryDto.getFamilyDiseases());

        return medicalHistory;
    }

}

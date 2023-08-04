package Patient.Service.Impl;

import Patient.Dtos.DiseaseHistoryDto;
import Patient.Exceptions.AppointmentNotFoundException;
import Patient.Exceptions.DiseaseHistoryNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.DiseaseHistory;
import Patient.Models.Patient;
import Patient.Repository.DiseaseHistoryRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.DiseaseHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
    public class DiseaseHistoryServiceImpl implements DiseaseHistoryService {
        private DiseaseHistoryRepository diseaseHistoryRepository;
        private PatientRepository patientRepository;

        public DiseaseHistoryServiceImpl(DiseaseHistoryRepository diseaseHistoryRepository, PatientRepository patientRepository) {
            this.diseaseHistoryRepository = diseaseHistoryRepository;
            this.patientRepository = patientRepository;
        }

    @Override
    public DiseaseHistoryDto createDiseaseHist(int patientId, DiseaseHistoryDto diseaseHistoryDto) {
        DiseaseHistory diseaseHistory = mapToEntity(diseaseHistoryDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new DiseaseHistoryNotFoundException("Patient with associated Disease History not found"));

        diseaseHistory.setPatient(patient);
        DiseaseHistory newDiseaseHistory = diseaseHistoryRepository.save(diseaseHistory);

        return mapToDto(newDiseaseHistory);
    }

    @Override
    public List<DiseaseHistoryDto> getDiseaseHistByPatientId(int id) {
        List<DiseaseHistory> diseaseHistories = diseaseHistoryRepository.findByPatientId(id);

        return diseaseHistories.stream().map((dh) -> mapToDto(dh)).collect(Collectors.toList());
    }

    @Override
    public DiseaseHistoryDto diseaseHistById(int patientId, int diseaseHistId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        List<DiseaseHistory> diseaseHistories = patient.getDeseaseHistories();

        DiseaseHistory diseaseHistory = diseaseHistories.stream()
                .filter(dh -> dh.getId() == diseaseHistId)
                .findFirst()
                .orElseThrow(() -> new DiseaseHistoryNotFoundException("Disease history not found"));

        return mapToDto(diseaseHistory);
    }


    @Override
    public DiseaseHistoryDto updateDiseaseHist(int patientId, int diseaseHistId, DiseaseHistoryDto diseaseHistoryDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated Disease History not found"));

        DiseaseHistory diseaseHistory = diseaseHistoryRepository.findById(diseaseHistId).orElseThrow(() ->
                new AppointmentNotFoundException("Disease History with associated disease History not found"));

        if(diseaseHistory.getPatient().getId() != patient.getId()){
            throw new DiseaseHistoryNotFoundException("This Disease History does not belong  to a patient");
        }

        diseaseHistory.setStartDate(diseaseHistoryDto.getStartDate());
        diseaseHistory.setDescription(diseaseHistoryDto.getDescription());
        diseaseHistory.setTreatment(diseaseHistoryDto.getTreatment());

        DiseaseHistory updateDiseaseHistory = diseaseHistoryRepository.save(diseaseHistory);

        return mapToDto(updateDiseaseHistory);
    }

    @Override
    public void deleteDisease(int patientId, int diseaseHistId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated Disease History not found"));

        DiseaseHistory diseaseHistory = diseaseHistoryRepository.findById(diseaseHistId).orElseThrow(() ->
                new AppointmentNotFoundException("Disease History does not belong to a patient"));

        if(diseaseHistory.getPatient().getId() != patient.getId()){
            throw new DiseaseHistoryNotFoundException("This Disease History does not belong  to a patient");
        }

        diseaseHistoryRepository.delete(diseaseHistory);
    }

    private DiseaseHistoryDto mapToDto(DiseaseHistory diseaseHistory){
        DiseaseHistoryDto diseaseHistoryDto = new DiseaseHistoryDto();
        diseaseHistoryDto.setId(diseaseHistory.getId());
        diseaseHistoryDto.setStartDate(diseaseHistory.getStartDate());
        diseaseHistoryDto.setDescription(diseaseHistory.getDescription());
        diseaseHistoryDto.setTreatment(diseaseHistory.getTreatment());

        return diseaseHistoryDto;
    }

    private DiseaseHistory mapToEntity(DiseaseHistoryDto diseaseHistoryDto){
        DiseaseHistory diseaseHistory = new DiseaseHistory();
        diseaseHistory.setId(diseaseHistoryDto.getId());
        diseaseHistory.setStartDate(diseaseHistoryDto.getStartDate());
        diseaseHistory.setDescription(diseaseHistoryDto.getDescription());
        diseaseHistory.setTreatment(diseaseHistoryDto.getTreatment());

        return diseaseHistory;
    }
}

package Patient.Service.Impl;

import Patient.Dtos.PhysicalExaminationDto;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Exceptions.PhysicalExaminationNotFoundException;
import Patient.Models.Patient;
import Patient.Models.PhysicalExamination;
import Patient.Repository.PatientRepository;
import Patient.Repository.PhysicalExaminationRepository;
import Patient.Service.PhysicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhysicalExaminationServiceImpl implements PhysicalExaminationService {
    private PhysicalExaminationRepository physicalExaminationRepository;
    private PatientRepository patientRepository;

    @Autowired
    public PhysicalExaminationServiceImpl(PhysicalExaminationRepository physicalExaminationRepository, PatientRepository patientRepository) {
        this.physicalExaminationRepository = physicalExaminationRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public PhysicalExaminationDto createPhysicalExamination(int patientId, PhysicalExaminationDto physicalExaminationDto) {
        PhysicalExamination physicalExamination = mapToEntity(physicalExaminationDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated physical examination not found"));

        physicalExamination.setPatient(patient);
        PhysicalExamination newPhysicalExamination = physicalExaminationRepository.save(physicalExamination);
        return mapToDto(newPhysicalExamination);
    }

    @Override
    public List<PhysicalExaminationDto> getPhysicalExamination(int id) {
        List<PhysicalExamination> physicalExaminations = physicalExaminationRepository.findByPatientId(id);

        return physicalExaminations.stream().map((pe) -> mapToDto(pe)).collect(Collectors.toList());
    }

    @Override
    public PhysicalExaminationDto physicalExaminationById(int patientId, int physicalExaminationId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        List<PhysicalExamination> physicalExaminations = patient.getPhysicalExaminations();

        PhysicalExamination physicalExamination = physicalExaminations.stream()
                .filter(pe -> pe.getId() == physicalExaminationId)
                .findFirst()
                .orElseThrow(() -> new PhysicalExaminationNotFoundException("Physical examination not found"));

        return mapToDto(physicalExamination);
    }

    @Override
    public PhysicalExaminationDto updatePhysicalExamination(int patientId, int physicalExaminationId, PhysicalExaminationDto physicalExaminationDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        PhysicalExamination physicalExamination = physicalExaminationRepository.findById(physicalExaminationId).orElseThrow(() ->
                new PhysicalExaminationNotFoundException("Physical examination not found"));

        if(physicalExamination.getPatient().getId() != patient.getId()){
            throw new PhysicalExaminationNotFoundException("This physical examination does not belong to a patient");
        }

        physicalExamination.setDate(physicalExaminationDto.getDate());
        physicalExamination.setBloodPressure(physicalExaminationDto.getBloodPressure());
        physicalExamination.setHeartRate(physicalExaminationDto.getHeartRate());
        physicalExamination.setHeartRhythm(physicalExaminationDto.getHeartRhythm());
        physicalExamination.setCardiacAuscultation(physicalExaminationDto.getCardiacAuscultation());
        physicalExamination.setLungAuscultation(physicalExaminationDto.getLungAuscultation());
        physicalExamination.setPeripheralEdema(physicalExaminationDto.getPeripheralEdema());
        physicalExamination.setPalpitations(physicalExaminationDto.getPalpitations());
        physicalExamination.setGeneralVitalSigns(physicalExaminationDto.getGeneralVitalSigns());

        PhysicalExamination updatePhysicalExamination = physicalExaminationRepository.save(physicalExamination);
        return mapToDto(updatePhysicalExamination);
    }

    @Override
    public void deletePhysicalExamination(int patientId, int physicalExaminationId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        PhysicalExamination physicalExamination = physicalExaminationRepository.findById(physicalExaminationId).orElseThrow(() ->
                new PhysicalExaminationNotFoundException("Physical examination not found"));

        if(physicalExamination.getPatient().getId() != patient.getId()){
            throw new PhysicalExaminationNotFoundException("This physical examination does not belong to a patient");
        }

        physicalExaminationRepository.delete(physicalExamination);
    }

    private PhysicalExaminationDto mapToDto(PhysicalExamination physicalExamination){
        PhysicalExaminationDto physicalExaminationDto = new PhysicalExaminationDto();
        physicalExaminationDto.setId(physicalExamination.getId());
        physicalExaminationDto.setDate(physicalExamination.getDate());
        physicalExaminationDto.setBloodPressure(physicalExamination.getBloodPressure());
        physicalExaminationDto.setHeartRate(physicalExamination.getHeartRate());
        physicalExaminationDto.setHeartRhythm(physicalExamination.getHeartRhythm());
        physicalExaminationDto.setCardiacAuscultation(physicalExamination.getCardiacAuscultation());
        physicalExaminationDto.setLungAuscultation(physicalExamination.getLungAuscultation());
        physicalExaminationDto.setPeripheralEdema(physicalExamination.getPeripheralEdema());
        physicalExaminationDto.setPalpitations(physicalExamination.getPalpitations());
        physicalExaminationDto.setGeneralVitalSigns(physicalExamination.getGeneralVitalSigns());

        return physicalExaminationDto;
    }

    private PhysicalExamination mapToEntity(PhysicalExaminationDto physicalExaminationDto){
        PhysicalExamination physicalExamination = new PhysicalExamination();
        physicalExamination.setId(physicalExaminationDto.getId());
        physicalExamination.setDate(physicalExaminationDto.getDate());
        physicalExamination.setBloodPressure(physicalExaminationDto.getBloodPressure());
        physicalExamination.setHeartRate(physicalExaminationDto.getHeartRate());
        physicalExamination.setHeartRhythm(physicalExaminationDto.getHeartRhythm());
        physicalExamination.setCardiacAuscultation(physicalExaminationDto.getCardiacAuscultation());
        physicalExamination.setLungAuscultation(physicalExaminationDto.getLungAuscultation());
        physicalExamination.setPeripheralEdema(physicalExaminationDto.getPeripheralEdema());
        physicalExamination.setPalpitations(physicalExaminationDto.getPalpitations());
        physicalExamination.setGeneralVitalSigns(physicalExaminationDto.getGeneralVitalSigns());

        return physicalExamination;
    }
}

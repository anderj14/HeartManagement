package Patient.Service.Impl;

import Patient.Dtos.TreatmentDto;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Exceptions.TreatmentNotFoundException;
import Patient.Models.Patient;
import Patient.Models.Treatment;
import Patient.Repository.PatientRepository;
import Patient.Repository.TreatmentRepository;
import Patient.Service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private TreatmentRepository treatmentRepository;
    private PatientRepository patientRepository;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, PatientRepository patientRepository) {
        this.treatmentRepository = treatmentRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public TreatmentDto createTreatment(int patientId, TreatmentDto treatmentDto) {
        Treatment treatment = mapToEntity(treatmentDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                 new TreatmentNotFoundException("Patient with associated treatment not found"));

        treatment.setPatient(patient);
        Treatment newTreatment = treatmentRepository.save(treatment);
        return mapToDto(newTreatment);
    }

    @Override
    public List<TreatmentDto> getTreatmentByPatientId(int id) {
        List<Treatment> treatments = treatmentRepository.findByPatientId(id);

        return treatments.stream().map(t -> mapToDto(t)).collect(Collectors.toList());
    }

    @Override
    public TreatmentDto TreatmentById(int patientId, int treatmentId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        List<Treatment> treatments = patient.getTreatments();
        Treatment treatment = treatments.stream()
                .filter(t -> t.getId() == treatmentId)
                .findFirst()
                .orElseThrow(() -> new TreatmentNotFoundException("Treatment not found"));

        return mapToDto(treatment);

    }

    @Override
    public TreatmentDto updateTreatment(int patientId, int treatmentId, TreatmentDto treatmentDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Treatment treatment = treatmentRepository.findById(treatmentId).orElseThrow(() ->
                new TreatmentNotFoundException("Treatment not found"));

        if(treatment.getPatient().getId() != patient.getId()){
            throw new TreatmentNotFoundException("This treatment does not belong to a patient");
        }

        treatment.setDate(treatmentDto.getDate());
        treatment.setMedication(treatmentDto.getMedication());
        treatment.setDosage(treatmentDto.getDosage());
        treatment.setInstructions(treatmentDto.getInstructions());
        treatment.setOtherTreatments(treatmentDto.getOtherTreatments());
        treatment.setSideEffects(treatmentDto.getSideEffects());
        treatment.setTreatmentMonitoring(treatmentDto.getTreatmentMonitoring());

        Treatment updateTreatment = treatmentRepository.save(treatment);
        return mapToDto(updateTreatment);
    }

    @Override
    public void deleteTreatment(int patientId, int treatmentId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Treatment treatment = treatmentRepository.findById(treatmentId).orElseThrow(() ->
                new TreatmentNotFoundException("Treatment not found"));


        if(treatment.getPatient().getId() != patient.getId()){
            throw new TreatmentNotFoundException("This treatment does not belong to a patient");
        }

        treatmentRepository.delete(treatment);
    }

    private TreatmentDto mapToDto(Treatment treatment){
        TreatmentDto treatmentDto = new TreatmentDto();
        treatmentDto.setId(treatment.getId());
        treatmentDto.setDate(treatment.getDate());
        treatmentDto.setMedication(treatment.getMedication());
        treatmentDto.setDosage(treatment.getDosage());
        treatmentDto.setInstructions(treatment.getInstructions());
        treatmentDto.setOtherTreatments(treatment.getOtherTreatments());
        treatmentDto.setSideEffects(treatment.getSideEffects());
        treatmentDto.setTreatmentMonitoring(treatment.getTreatmentMonitoring());

        return treatmentDto;
    }

    private Treatment mapToEntity(TreatmentDto treatmentDto){
        Treatment treatment = new Treatment();
        treatment.setId(treatmentDto.getId());
        treatment.setDate(treatmentDto.getDate());
        treatment.setMedication(treatmentDto.getMedication());
        treatment.setDosage(treatmentDto.getDosage());
        treatment.setInstructions(treatmentDto.getInstructions());
        treatment.setOtherTreatments(treatmentDto.getOtherTreatments());
        treatment.setSideEffects(treatmentDto.getSideEffects());
        treatment.setTreatmentMonitoring(treatmentDto.getTreatmentMonitoring());

        return treatment;
    }
}

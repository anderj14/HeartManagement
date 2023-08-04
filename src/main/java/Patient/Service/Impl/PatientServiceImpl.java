package Patient.Service.Impl;

import Patient.Dtos.PatientDto;
import Patient.Dtos.PatientResponse;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.Patient;
import Patient.Repository.PatientRepository;
import Patient.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientResponse getPatients(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Patient> patients = patientRepository.findAll(pageable);
        List<Patient> listOfPatient = patients.getContent();
        List<PatientDto> content = listOfPatient.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setContent(content);
        patientResponse.setPageNo(patients.getNumber());
        patientResponse.setPageSize(patients.getSize());
        patientResponse.setTotalElements(patients.getTotalElements());
        patientResponse.setTotalPages(patients.getTotalPages());
        patientResponse.setLast(patients.isLast());

        return patientResponse;
    }

    @Override
    public PatientDto getPatientById(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient could no be found"));

        return mapToDto(patient);
    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = mapToEntity(patientDto);
        Patient newPatient = patientRepository.save(patient);
        PatientDto patientResponse = mapToDto(newPatient);
        return patientResponse;
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto, int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient could not be update"));

        Patient updatePatient = mapToEntity(patientDto);
        updatePatient.setId(patient.getId());


        Patient savedPatient = patientRepository.save(updatePatient);
        PatientDto response = mapToDto(savedPatient);
        return response;
    }

    @Override
    public void deletePatient(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient could no be delete"));
        patientRepository.delete(patient);
    }

    private PatientDto mapToDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setPersonId(patient.getPersonId());
        patientDto.setDOB(patient.getDOB());
        patientDto.setGender(patient.getGender());
        patientDto.setAddress(patient.getAddress());
        patientDto.setPhone(patient.getPhone());
        patientDto.setEmail(patient.getEmail());
        patientDto.setNoMedicare(patient.getNoMedicare());

        return patientDto;
    }

    private Patient mapToEntity(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setPersonId(patientDto.getPersonId());
        patient.setDOB(patientDto.getDOB());
        patient.setGender(patientDto.getGender());
        patient.setAddress(patientDto.getAddress());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setNoMedicare(patientDto.getNoMedicare());

        return patient;
    }
}

package Patient.Service;

import Patient.Dtos.PatientDto;
import Patient.Dtos.PatientResponse;

public interface PatientService {
    PatientResponse getPatients(int pageNo, int pageSize);
    PatientDto getPatientById(int id);
    PatientDto createPatient(PatientDto patientDto);
    PatientDto updatePatient(PatientDto patientDto, int id);
    void deletePatient(int id);
}

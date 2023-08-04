package Patient.Service.Impl;

import Patient.Dtos.DiagnosticDto;
import Patient.Exceptions.DiagnosticNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.Diagnostic;
import Patient.Models.Patient;
import Patient.Repository.DiagnosticRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {

    private DiagnosticRepository diagnosticRepository;
    private PatientRepository patientRepository;

    @Autowired
    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository, PatientRepository patientRepository) {
        this.diagnosticRepository = diagnosticRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public DiagnosticDto createDiagnostic(int patientId, DiagnosticDto diagnosticDto) {
       Diagnostic diagnostic = mapToEntity(diagnosticDto);
       Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
               new PatientNotFoundException("Patient with associated echocardiogram not found"));

       diagnostic.setPatient(patient);
       Diagnostic newDiagnostic = diagnosticRepository.save(diagnostic);
       return mapToDto(newDiagnostic);
    }

    @Override
    public List<DiagnosticDto> getDiagnosticByPatientId(int id) {
        List<Diagnostic> diagnostics = diagnosticRepository.findByPatientId(id);

        return diagnostics.stream().map(d -> mapToDto(d)).collect(Collectors.toList());
    }

    @Override
    public DiagnosticDto diagnosticById(int patientId, int diagnosticId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated echocardiogram not found"));

        List<Diagnostic> diagnostics = patient.getDiagnostics();
        Diagnostic diagnostic = diagnostics.stream()
                .filter(d -> d.getId() == diagnosticId)
                .findFirst()
                .orElseThrow(() -> new DiagnosticNotFoundException("Diagnostic not found"));

        return mapToDto(diagnostic);

    }

    @Override
    public DiagnosticDto updateDiagnostic(int patientId, int diagnosticId, DiagnosticDto diagnosticDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Diagnostic diagnostic = diagnosticRepository.findById(diagnosticId).orElseThrow(() ->
                new DiagnosticNotFoundException("Diagnostic not found"));

        if(diagnostic.getPatient().getId() != patient.getId()){
            throw new DiagnosticNotFoundException("This diagnostic does not belong to a patient");
        }

        diagnostic.setDate(diagnosticDto.getDate());
        diagnostic.setConditionName(diagnosticDto.getConditionName());
        diagnostic.setDescription(diagnosticDto.getDescription());
        diagnostic.setClassificationCondition(diagnosticDto.getClassificationCondition());
        diagnostic.setSeverity(diagnosticDto.getSeverity());
        diagnostic.setRiskAssessment(diagnosticDto.getRiskAssessment());
        diagnostic.setConclusions(diagnosticDto.getConclusions());

        Diagnostic updateDiagnostic = diagnosticRepository.save(diagnostic);
        return mapToDto(updateDiagnostic);
    }

    @Override
    public void deleteDiagnostic(int patientId, int diagnosticId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Diagnostic diagnostic = diagnosticRepository.findById(diagnosticId).orElseThrow(() ->
                new DiagnosticNotFoundException("Diagnostic not found"));

        if(diagnostic.getPatient().getId() != patient.getId()){
            throw new DiagnosticNotFoundException("This diagnostic does not belong to a patient");
        }

        diagnosticRepository.delete(diagnostic);
    }

    private DiagnosticDto mapToDto(Diagnostic diagnostic){
        DiagnosticDto diagnosticDto = new DiagnosticDto();
        diagnosticDto.setId(diagnostic.getId());
        diagnosticDto.setDate(diagnostic.getDate());
        diagnosticDto.setConditionName(diagnostic.getConditionName());
        diagnosticDto.setDescription(diagnostic.getDescription());
        diagnosticDto.setClassificationCondition(diagnostic.getClassificationCondition());
        diagnosticDto.setSeverity(diagnostic.getSeverity());
        diagnosticDto.setRiskAssessment(diagnostic.getRiskAssessment());
        diagnosticDto.setConclusions(diagnostic.getConclusions());

        return diagnosticDto;
    }

    private Diagnostic mapToEntity(DiagnosticDto diagnosticDto){
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setId(diagnosticDto.getId());
        diagnostic.setDate(diagnosticDto.getDate());
        diagnostic.setConditionName(diagnosticDto.getConditionName());
        diagnostic.setDescription(diagnosticDto.getDescription());
        diagnostic.setClassificationCondition(diagnosticDto.getClassificationCondition());
        diagnostic.setSeverity(diagnosticDto.getSeverity());
        diagnostic.setRiskAssessment(diagnosticDto.getRiskAssessment());
        diagnostic.setConclusions(diagnosticDto.getConclusions());

        return diagnostic;
    }
}

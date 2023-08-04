package Patient.Service;

import Patient.Dtos.DiagnosticDto;
import java.util.List;
public interface DiagnosticService {
    DiagnosticDto createDiagnostic(int patientId, DiagnosticDto diagnosticDto);
    List<DiagnosticDto> getDiagnosticByPatientId(int id);
    DiagnosticDto diagnosticById(int patientId, int diagnosticId);
    DiagnosticDto updateDiagnostic(int patientId, int diagnosticId, DiagnosticDto diagnosticDto);
    void deleteDiagnostic(int patientId, int diagnosticId);
}

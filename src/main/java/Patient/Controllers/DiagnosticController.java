package Patient.Controllers;

import Patient.Dtos.DiagnosticDto;
import Patient.Service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class DiagnosticController {
    private DiagnosticService diagnosticService;

    @Autowired
    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @PostMapping("patient/{patientId}/createDiagnostic")
    public ResponseEntity<DiagnosticDto> createDiagnostic(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody DiagnosticDto diagnosticDto
    ){
        return new ResponseEntity<>(diagnosticService.createDiagnostic(patientId, diagnosticDto), HttpStatus.CREATED);
    }

    @GetMapping("patient/{patientId}/diagnostics")
    public List<DiagnosticDto> getDiagnosticsByPatientId(
            @PathVariable(value = "patientId") int patientId
    ){
        return diagnosticService.getDiagnosticByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/diagnostic/{id}")
    public ResponseEntity<DiagnosticDto> diagnosticByPatientId(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int diagnosticId
    ){
        DiagnosticDto diagnosticDto = diagnosticService.diagnosticById(patientId, diagnosticId);
        return new ResponseEntity<>(diagnosticDto,  HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/updateDiagnostic/{id}")
    public ResponseEntity<DiagnosticDto> updateDiagnostic(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int diagnosticId,
            @RequestBody DiagnosticDto diagnosticDto
    ){
        DiagnosticDto updateDiagnosticDto = diagnosticService.updateDiagnostic(patientId, diagnosticId, diagnosticDto);
        return new ResponseEntity<>(updateDiagnosticDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/deleteDiagnostic/{id}")
    public ResponseEntity<String> deleteDiagnostic(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int diagnosticId
    ){
        diagnosticService.deleteDiagnostic(patientId, diagnosticId);
        return new ResponseEntity<>("Diagnostic delete", HttpStatus.OK);
    }
}













package Patient.Controllers;

import Patient.Dtos.MedicalHistoryDto;
import Patient.Service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class MedicalHistoryController {

    private MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping("/patient/{patientId}/medicalHistory")
    public ResponseEntity<MedicalHistoryDto> createMedicalHistory(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody MedicalHistoryDto medicalHistoryDto
    ){
        return new ResponseEntity<>(
                medicalHistoryService.createMedicalHistory(patientId, medicalHistoryDto), HttpStatus.CREATED
        );
    }

    @GetMapping("/patient/{PatientId}/medicalHistories")
    public List<MedicalHistoryDto> getMedicalHistoriesByPatientId(
            @PathVariable(value = "PatientId") int patientId
    ){
        return medicalHistoryService.getMedicalHistoriesByPatientId(patientId);
    }

    @GetMapping("/patient/{patientId}/medicalHistory/{id}")
    public ResponseEntity<MedicalHistoryDto> getMedicalHistoryById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int medicalHistoryId
    ){
        MedicalHistoryDto medicalHistoryDto = medicalHistoryService.medicalHistoryById(patientId, medicalHistoryId);
        return new ResponseEntity<>(medicalHistoryDto, HttpStatus.OK);
    }

    @PutMapping("/patient/{patientId}/medicalHistory/{id}")
    public ResponseEntity<MedicalHistoryDto> updateMedicalHistory(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int medicalHistoryId,
            @RequestBody MedicalHistoryDto medicalHistoryDto
    ){
        MedicalHistoryDto updateMedicalHistoryDto =
                medicalHistoryService.updateMedicalHistory(patientId, medicalHistoryId, medicalHistoryDto);

        return new ResponseEntity<>(updateMedicalHistoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/patient/{patientId}/medicalHistory/{id}")
    public ResponseEntity<String> deleteMedicalHistory(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int medicalHistoryId
    ){
        medicalHistoryService.deleteMedicalHistory(patientId, medicalHistoryId);

        return new ResponseEntity<>("Medical History delete", HttpStatus.OK);
    }
}

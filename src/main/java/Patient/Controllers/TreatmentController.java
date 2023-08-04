package Patient.Controllers;

import Patient.Dtos.TreatmentDto;
import Patient.Service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class TreatmentController {

    private TreatmentService treatmentService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping("patient/{patientId}/createTreatment")
    public ResponseEntity<TreatmentDto> createTreatment(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody TreatmentDto treatmentDto
            ){
        return new ResponseEntity<>(
                treatmentService.createTreatment(patientId, treatmentDto), HttpStatus.CREATED
        );
    }

    @GetMapping("patient/{patientId}/treatments")
    public List<TreatmentDto> getTreatmentsByPatientId(
            @PathVariable(value = "patientId") int patientId
    ){
        return treatmentService.getTreatmentByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/treatment/{id}")
    public ResponseEntity<TreatmentDto> getTreatmentByPatientId(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int treatmentId
    ){
        TreatmentDto treatmentDto = treatmentService.TreatmentById(patientId, treatmentId);
        return new ResponseEntity<>(treatmentDto, HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/updateTreatment/{id}")
    public ResponseEntity<TreatmentDto> updateTreatment(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int treatmentId,
            @RequestBody TreatmentDto treatmentDto
    ){
        TreatmentDto updateTreatmentDto = treatmentService.updateTreatment(patientId, treatmentId, treatmentDto);
        return new ResponseEntity<>(updateTreatmentDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/deleteTreatment/{id}")
    public ResponseEntity<String> deleteTreatment(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int treatmentId
    ){
        treatmentService.deleteTreatment(patientId, treatmentId);
        return new ResponseEntity<>("Treatment delete", HttpStatus.OK);
    }
}

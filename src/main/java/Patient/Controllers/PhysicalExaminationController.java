package Patient.Controllers;

import Patient.Dtos.PhysicalExaminationDto;
import Patient.Service.PhysicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PhysicalExaminationController {
    private PhysicalExaminationService physicalExaminationService;

    @Autowired
    public PhysicalExaminationController(PhysicalExaminationService physicalExaminationService) {
        this.physicalExaminationService = physicalExaminationService;
    }

    @PostMapping("/patient/{patientId}/physicalExamination")
    public ResponseEntity<PhysicalExaminationDto> createPhysicalExamination(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody PhysicalExaminationDto physicalExaminationDto
    ){
        return new ResponseEntity<>(
                physicalExaminationService.createPhysicalExamination(patientId, physicalExaminationDto), HttpStatus.CREATED
        );
    }

    @GetMapping("/patient/{patientId}/physicalExaminations")
    public List<PhysicalExaminationDto> getPhysicalExaminations(
            @PathVariable(value = "patientId") int patientId
    ){
        return physicalExaminationService.getPhysicalExamination(patientId);
    }

    @GetMapping("/patient/{patientId}/physicalExamination/{id}")
    public ResponseEntity<PhysicalExaminationDto> getPhysicalExaminationById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int physicalExaminationId
    ){
        PhysicalExaminationDto physicalExaminationDto = physicalExaminationService.physicalExaminationById(patientId, physicalExaminationId);
        return new ResponseEntity<>(physicalExaminationDto, HttpStatus.OK);
    }

    @PutMapping("/patient/{patientId}/physicalExamination/{id}")
    public ResponseEntity<PhysicalExaminationDto> updatePhysicalExamination(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int physicalExaminationId,
            @RequestBody PhysicalExaminationDto physicalExaminationDto
    ){
        PhysicalExaminationDto updatePhysicalExaminationDto =
                physicalExaminationService.updatePhysicalExamination(patientId, physicalExaminationId, physicalExaminationDto);

        return new ResponseEntity<>(updatePhysicalExaminationDto, HttpStatus.OK);
    }

    @DeleteMapping("/patient/{patientId}/physicalExamination/{id}")
    public ResponseEntity<String> deletePhysicalExamination(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int physicalExaminationId
    ){
        physicalExaminationService.deletePhysicalExamination(patientId, physicalExaminationId);
        return new ResponseEntity<>("Physical examination delete", HttpStatus.OK);
    }
}

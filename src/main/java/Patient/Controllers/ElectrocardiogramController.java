package Patient.Controllers;

import Patient.Dtos.ElectrocardiogramDto;
import Patient.Service.ElectrocardiogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ElectrocardiogramController {

    private ElectrocardiogramService electrocardiogramService;

    @Autowired
    public ElectrocardiogramController(ElectrocardiogramService electrocardiogramService) {
        this.electrocardiogramService = electrocardiogramService;
    }

    @PostMapping("/patient/{patientId}/electrocardiogram")
    public ResponseEntity<ElectrocardiogramDto> createElectrocardiogram(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody ElectrocardiogramDto electrocardiogramDto
    ){
        return new ResponseEntity<>(
                electrocardiogramService.createElectrocardiogram(patientId, electrocardiogramDto), HttpStatus.CREATED
        );
    }

    @GetMapping("patient/{patientId}/electrocardiograms")
    public List<ElectrocardiogramDto> getElectrocardiogramsByPatientId(
            @PathVariable(value = "patientId") int patientId
    ){
        return electrocardiogramService.getElectrocardiogramsByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/electrocardiogram/{id}")
    public ResponseEntity<ElectrocardiogramDto> getElectrocardiogramById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int electrocardiogramId
    ){
        ElectrocardiogramDto electrocardiogramDto = electrocardiogramService.electrocardiogramById(patientId, electrocardiogramId);
        return new ResponseEntity<>(electrocardiogramDto, HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/electrocardiogram/{id}")
    public ResponseEntity<ElectrocardiogramDto> updateElectrocardiogram(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int electrocardiogramId,
            @RequestBody ElectrocardiogramDto electrocardiogramDto
    ){
        ElectrocardiogramDto updateElectrocardiogramDto =
                electrocardiogramService.updateElectrocardiogram(patientId, electrocardiogramId, electrocardiogramDto);

        return new ResponseEntity<>(updateElectrocardiogramDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/electrocardiogram/{id}")
    public ResponseEntity<String> deleteElectrocardiogram(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int electrocardiogramId
    ){
        electrocardiogramService.deleteElectrocardiogram(patientId, electrocardiogramId);
        return new ResponseEntity<>("Delete electrocardiogram", HttpStatus.OK);
    }
}

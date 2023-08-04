package Patient.Controllers;

import Patient.Dtos.EchocardiogramDto;
import Patient.Service.EchocardiogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class EchocardiogramController {
    private EchocardiogramService echocardiogramService;

    @Autowired
    public EchocardiogramController(EchocardiogramService echocardiogramService) {
        this.echocardiogramService = echocardiogramService;
    }

    @PostMapping("patient/{patientId}/echocardiogram")
    public ResponseEntity<EchocardiogramDto> createEchocardiogram(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody EchocardiogramDto echocardiogramDto
    ){
        return new ResponseEntity<>(
                echocardiogramService.createEchocardiogram(patientId, echocardiogramDto), HttpStatus.CREATED
        );
    }

    @GetMapping("patient/{patientId}/echocardiograms")
    public List<EchocardiogramDto> getEchocardiogramsByPatientId(
            @PathVariable(value = "patientId") int patientId
    ){
        return echocardiogramService.getEchocardiogramsByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/echocardiogram/{id}")
    public ResponseEntity<EchocardiogramDto> getEchocardiogramById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int echocardiogramId
    ){
        EchocardiogramDto echocardiogramDto = echocardiogramService.echocardiogramById(patientId, echocardiogramId);
        return new ResponseEntity<>(echocardiogramDto, HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/echocardiogram/{id}")
    public ResponseEntity<EchocardiogramDto> updateEchocardiogram(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int echocardiogramId,
            @RequestBody EchocardiogramDto echocardiogramDto
    ){
        EchocardiogramDto updateEchocardiogramDto =
                echocardiogramService.updateEchocardiogram(patientId, echocardiogramId, echocardiogramDto);
        return new ResponseEntity<>(updateEchocardiogramDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/echocardiogram/{id}")
    public ResponseEntity<String> deleteEchocardiogram(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int echocardiogramId
    ){

        echocardiogramService.deleteEchocardiogram(patientId, echocardiogramId);
        return new ResponseEntity<>("Echocardiogram delete", HttpStatus.OK);
    }

}

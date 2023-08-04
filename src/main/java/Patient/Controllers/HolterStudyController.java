package Patient.Controllers;

import Patient.Dtos.HolterStudyDto;
import Patient.Service.HolterStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class HolterStudyController {
    private HolterStudyService holterStudyService;

    @Autowired
    public HolterStudyController(HolterStudyService holterStudyService) {
        this.holterStudyService = holterStudyService;
    }

    @PostMapping("patient/{patientId}/createHolterStudy")
    public ResponseEntity<HolterStudyDto> createHolterStudy(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody HolterStudyDto holterStudyDto
    ){
        return new ResponseEntity<>(
                holterStudyService.createHolterStudy(patientId, holterStudyDto), HttpStatus.CREATED
        );
    }

    @GetMapping("patient/{patientId}/holterStudies")
    public List<HolterStudyDto> getHolterStudiesByPatientId(
            @PathVariable(value = "patientId") int patientId
    ){
        return holterStudyService.getHolterStudiesByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/holterStudy/{id}")
    public ResponseEntity<HolterStudyDto> getHolterStudyById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int holterStudyId
    ){
        HolterStudyDto holterStudyDto = holterStudyService.holterStudyById(patientId, holterStudyId);
        return new ResponseEntity<>(holterStudyDto, HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/updateHolterStudy/{id}")
    public ResponseEntity<HolterStudyDto> updateHolterStudy(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int holterStudyId,
            @RequestBody HolterStudyDto holterStudyDto
    ){
        HolterStudyDto updateHolterStudyDto =
                holterStudyService.updateHolterStudy(patientId, holterStudyId, holterStudyDto);

        return new ResponseEntity<>(updateHolterStudyDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/deleteHolterStudy/{id}")
    public ResponseEntity<String> deleteHolterStudy(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int holterStudyId
    ){
        holterStudyService.deleteHolterStudy(patientId, holterStudyId);
        return new ResponseEntity<>("Holter study delete", HttpStatus.OK);
    }

}

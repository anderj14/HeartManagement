package Patient.Controllers;

import Patient.Dtos.BloodTestDto;
import Patient.Service.BloodTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BloodTestController {
    private BloodTestService bloodTestService;

    @Autowired
    public BloodTestController(BloodTestService bloodTestService) {
        this.bloodTestService = bloodTestService;
    }

    @PostMapping("patient/{patientId}/bloodTest")
    public ResponseEntity<BloodTestDto> createBloodTest(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody BloodTestDto bloodTestDto
    ){
        return new ResponseEntity<>(bloodTestService.createBloodTest(patientId, bloodTestDto), HttpStatus.CREATED);
    }

    @GetMapping("patient/{patientId}/bloodTests")
    public List<BloodTestDto> getBloodTests(
            @PathVariable(value = "patientId") int patientId
    ){
        return bloodTestService.getBloodTestsByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/bloodTest/{id}")
    public ResponseEntity<BloodTestDto> getBloodTestById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int bloodTestId
    ){
        BloodTestDto bloodTestDto = bloodTestService.bloodTestById(patientId, bloodTestId);
        return new ResponseEntity<>(bloodTestDto, HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/bloodTest/{id}")
    public ResponseEntity<BloodTestDto> updateBloodTest(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int bloodTestId,
            @RequestBody BloodTestDto bloodTestDto
    ){
        BloodTestDto updateBloodTestDto = bloodTestService.updateBloodTestDto(patientId, bloodTestId, bloodTestDto);;
        return new ResponseEntity<>(updateBloodTestDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/bloodTest/{id}")
    public ResponseEntity<String> deleteBloodTest(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int bloodTestId
    ){
        bloodTestService.deleteBloodTest(patientId, bloodTestId);
        return new ResponseEntity<>("Blood test  delete", HttpStatus.OK);
    }
}

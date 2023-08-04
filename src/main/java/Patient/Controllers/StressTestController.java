package Patient.Controllers;

import Patient.Dtos.StressTestDto;
import Patient.Service.StressTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class StressTestController {
    private StressTestService stressTestService;

    @Autowired
    public StressTestController(StressTestService stressTestService) {
        this.stressTestService = stressTestService;
    }

    @PostMapping("patient/{patientId}/stressTest")
    public ResponseEntity<StressTestDto> createStressTest(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody StressTestDto stressTestDto
    ){
        return  new ResponseEntity<>(
                stressTestService.createStressTest(patientId, stressTestDto), HttpStatus.CREATED
        );
    }

    @GetMapping("patient/{patientId}/stressTests")
    public List<StressTestDto> getStressTestsByPatientId(
            @PathVariable(value = "patientId") int patientId
    ){
        return stressTestService.getStressTestsByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/stressTest/{id}")
    public ResponseEntity<StressTestDto> getEchocardiogramById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int stressTestId
    ){
        StressTestDto stressTestDto = stressTestService.stressTestId(patientId, stressTestId);
        return new ResponseEntity<>(stressTestDto, HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/stressTest/{id}")
    public ResponseEntity<StressTestDto> updateStressTest(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int stressTestId,
            @RequestBody StressTestDto stressTestDto
    ){
        StressTestDto updateStressTestDto = stressTestService.updateStressTest(patientId, stressTestId, stressTestDto);
        return new ResponseEntity<>(updateStressTestDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/stressTest/{id}")
    public ResponseEntity<String> deleteStressTest(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int stressTestId
    ){
        stressTestService.deleteStressTest(patientId, stressTestId);
        return new ResponseEntity<>("Stress test delete", HttpStatus.OK);
    }
}

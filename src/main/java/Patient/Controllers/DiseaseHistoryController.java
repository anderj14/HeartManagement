package Patient.Controllers;

import Patient.Dtos.DiseaseHistoryDto;
import Patient.Service.DiseaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class DiseaseHistoryController {
    private DiseaseHistoryService diseaseHistoryService;

    @Autowired
    public DiseaseHistoryController(DiseaseHistoryService diseaseHistoryService) {
        this.diseaseHistoryService = diseaseHistoryService;
    }

    @PostMapping("/patient/{patientId}/diseaseHistory")
    public ResponseEntity<DiseaseHistoryDto> createDiseaseHistory(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody DiseaseHistoryDto diseaseHistoryDto
    ){
        return new ResponseEntity<>(
                diseaseHistoryService.createDiseaseHist(patientId, diseaseHistoryDto), HttpStatus.CREATED
        );
    }

    @GetMapping("/patient/{patientId}/diseaseHistories")
    public List<DiseaseHistoryDto> getDiseaseHistoryByPatientId(
            @PathVariable(value = "patientId") int patientId
    ){
        return diseaseHistoryService.getDiseaseHistByPatientId(patientId);
    }

    @GetMapping("/patient/{patientId}/diseaseHistory/{id}")
    public ResponseEntity<DiseaseHistoryDto> getDiseaseHistoryById(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int diseaseHistoryId
    ){
        DiseaseHistoryDto diseaseHistoryDto = diseaseHistoryService.diseaseHistById(patientId, diseaseHistoryId);

        return new ResponseEntity<>(diseaseHistoryDto, HttpStatus.OK);
    }

    @PutMapping("/patient/{patientId}/diseaseHistory/{id}")
    public ResponseEntity<DiseaseHistoryDto> updateDiseaseHistory(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int diseaseHistoryId,
            @RequestBody DiseaseHistoryDto diseaseHistoryDto
    ){
        DiseaseHistoryDto updateDiseaseHistoryDto =
                diseaseHistoryService.updateDiseaseHist(patientId, diseaseHistoryId, diseaseHistoryDto);

        return new ResponseEntity<>(updateDiseaseHistoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/patient/{patientId}/diseaseHistory/{id}")
    public ResponseEntity<String> deleteDiseaseHistory(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int diseaseHistory
    ){
        diseaseHistoryService.deleteDisease(patientId, diseaseHistory);

        return new ResponseEntity<>("Disease History delete", HttpStatus.OK);
    }
}

package Patient.Controllers;

import Patient.Dtos.CardiacCatheterizationStudyDto;
import Patient.Service.CardiacCatheterizationStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CardiacCatheterizationStudyController {

    private CardiacCatheterizationStudyService cardiacCathStService;

    @Autowired
    public CardiacCatheterizationStudyController(CardiacCatheterizationStudyService cardiacCathStService) {
        this.cardiacCathStService = cardiacCathStService;
    }

    @PostMapping("patient/{patientId}/cardiacCathStudy")
    public ResponseEntity<CardiacCatheterizationStudyDto> createCardiacCathStudy(
            @PathVariable(value = "patientId") int patientId,
            @RequestBody CardiacCatheterizationStudyDto cardiacCathStDto
    ){
        return new ResponseEntity<>(
                cardiacCathStService.createCardiacCatheterizationStudy(patientId, cardiacCathStDto), HttpStatus.CREATED
        );
    }

    @GetMapping("patient/{patientId}/cardiacCathStudies")
    public List<CardiacCatheterizationStudyDto> getCardiacCathStudies(
            @PathVariable(value = "patientId") int patientId
    ){
        return cardiacCathStService.getCardiacCatheterizationStudiesByPatientId(patientId);
    }

    @GetMapping("patient/{patientId}/cardiacCathStudy/{id}")
    public ResponseEntity<CardiacCatheterizationStudyDto> getCardiacCathStudy(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int cardiacCathStudyDtoId
    ){
        CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto =
                cardiacCathStService.CardiacCatheterizationStudyById(patientId, cardiacCathStudyDtoId);
        return new ResponseEntity<>(cardiacCatheterizationStudyDto, HttpStatus.OK);
    }

    @PutMapping("patient/{patientId}/cardiacCathStudy/{id}")
    public ResponseEntity<CardiacCatheterizationStudyDto> updateCardiacCathStudy(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int cardiacCathStudyDtoId,
            @RequestBody CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto
    ){
        CardiacCatheterizationStudyDto updateCardiacCathStudyDto =
                cardiacCathStService.updateCardiacCatheterizationStudyById(patientId, cardiacCathStudyDtoId, cardiacCatheterizationStudyDto);
        return new ResponseEntity<>(updateCardiacCathStudyDto, HttpStatus.OK);
    }

    @DeleteMapping("patient/{patientId}/cardiacCathStudy/{id}")
    public ResponseEntity<String> deleteCardiacCathStudy(
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "id") int cardiacCathStudyDtoId
    ){
        cardiacCathStService.deleteCardiacCatheterizationStudyById(patientId, cardiacCathStudyDtoId);
        return new ResponseEntity<>("Cardiac catheterization study", HttpStatus.OK);
    }
}

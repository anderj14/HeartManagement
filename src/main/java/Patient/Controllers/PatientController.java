package Patient.Controllers;

import Patient.Dtos.PatientDto;
import Patient.Dtos.PatientResponse;
import Patient.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("patient/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){
        return new ResponseEntity<>(patientService.createPatient(patientDto), HttpStatus.CREATED);
    }

    @GetMapping("patient")
    public ResponseEntity<PatientResponse> getPatients(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(patientService.getPatients(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("patient/{id}")
    public ResponseEntity<PatientDto> patientDetail(@PathVariable int id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("patient/{id}/update")
    public ResponseEntity<PatientDto> updatePatient(
            @RequestBody PatientDto patientDto,
            @PathVariable("id") int id
    ){
        PatientDto response = patientService.updatePatient(patientDto, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("patient/{id}/delete")
    public ResponseEntity<String> deletePatient(@PathVariable("id") int patientId){
        patientService.deletePatient(patientId);
        return new ResponseEntity<>("Patient Delete", HttpStatus.OK);
    }

}

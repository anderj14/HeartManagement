package Patient.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerPatientNotFoundException(
            PatientNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerAppointmentNotFoundException(
            AppointmentNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DiseaseHistoryNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerDiseaseHistoryNotFoundException(
            DiseaseHistoryNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicalHistoryNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerMedicalHistoryNotFoundException(
            MedicalHistoryNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhysicalExaminationNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerPhysicalExaminationNotFoundException(
            PhysicalExaminationNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElectrocardiogramNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerElectrocardiogramNotFoundException(
            ElectrocardiogramNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EchocardiogramNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerEchocardiogramNotFoundException(
            EchocardiogramNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StressTestNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerStressTestNotFoundException(
            StressTestNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HolterStudyNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerHolterStudyNotFoundException(
            HolterStudyNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardiacCatheterizationStudyNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerCardiacCatheterizationStudyNotFoundException(
            CardiacCatheterizationStudyNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BloodTestNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerBloodTestNotFoundException(
            BloodTestNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DiagnosticNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerDiagnosticNotFoundException(
            DiagnosticNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TreatmentNotFoundException.class)
    public ResponseEntity<ErrorObject> handlerTreatmentNotFoundException(
            TreatmentNotFoundException ex, WebRequest request
    ){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
}

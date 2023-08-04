package Patient.Exceptions;

public class TreatmentNotFoundException extends RuntimeException{
    public TreatmentNotFoundException(String message) {
        super(message);
    }
}

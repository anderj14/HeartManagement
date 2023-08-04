package Patient.Exceptions;

public class HolterStudyNotFoundException extends RuntimeException{
    public HolterStudyNotFoundException(String message) {
        super(message);
    }
}

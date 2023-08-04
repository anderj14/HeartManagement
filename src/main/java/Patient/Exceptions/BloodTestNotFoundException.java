package Patient.Exceptions;

public class BloodTestNotFoundException extends RuntimeException{
    public BloodTestNotFoundException(String message) {
        super(message);
    }
}

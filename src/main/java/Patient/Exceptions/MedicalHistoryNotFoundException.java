package Patient.Exceptions;

public class MedicalHistoryNotFoundException extends RuntimeException{
//    private static final long serialVerisionUID = 4;

    public MedicalHistoryNotFoundException(String message) {
        super(message);
    }
}

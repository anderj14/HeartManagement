package Patient.Exceptions;

public class PhysicalExaminationNotFoundException extends RuntimeException{
//    private static final long serialVerisionUID = 5;

    public PhysicalExaminationNotFoundException(String message) {
        super(message);
    }
}

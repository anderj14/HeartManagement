package Patient.Exceptions;

public class ElectrocardiogramNotFoundException extends RuntimeException{
//    private static final long serialVerisionUID = 6;

    public ElectrocardiogramNotFoundException(String message) {
        super(message);
    }
}

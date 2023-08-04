package Patient.Exceptions;

public class EchocardiogramNotFoundException extends RuntimeException{
//    private static final long serialVerisionUID = 7;

    public EchocardiogramNotFoundException(String message) {
        super(message);
    }
}

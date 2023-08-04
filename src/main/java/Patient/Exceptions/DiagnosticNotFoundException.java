package Patient.Exceptions;

public class DiagnosticNotFoundException extends RuntimeException{
    public DiagnosticNotFoundException(String message) {
        super(message);
    }
}

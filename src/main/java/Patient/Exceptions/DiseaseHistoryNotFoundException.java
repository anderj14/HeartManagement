package Patient.Exceptions;

public class DiseaseHistoryNotFoundException extends RuntimeException{

//    private static final long serialVerisionUID = 3;


    public DiseaseHistoryNotFoundException(String message) {
        super(message);
    }
}

package by.sum_solutions.findtrip.exception;

public class EditParameterIsExistException extends RuntimeException {

    Long idUser;

    public EditParameterIsExistException() {
    }

    public EditParameterIsExistException(String message) {
        super(message);
    }
    public EditParameterIsExistException(String message, Long id) {
        super(message);
        idUser = id;
    }

    public EditParameterIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EditParameterIsExistException(Throwable cause) {
        super(cause);
    }


}

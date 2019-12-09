package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.UserDTO;

public class RegistrationParameterIsExistException extends RuntimeException {

    UserDTO userDTO;

    public RegistrationParameterIsExistException() {
    }

    public RegistrationParameterIsExistException(String message) {
        super(message);
    }

    public RegistrationParameterIsExistException(String message, UserDTO userDTO) {
        super(message);
        this.userDTO = userDTO;
    }

    public RegistrationParameterIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationParameterIsExistException(Throwable cause) {
        super(cause);
    }


}

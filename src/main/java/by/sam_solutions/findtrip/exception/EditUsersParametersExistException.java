package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.UserDTO;

public class EditUsersParametersExistException extends RuntimeException {

    UserDTO userDTO;

    public EditUsersParametersExistException() {
    }

    public EditUsersParametersExistException(String message) {
        super(message);
    }

    public EditUsersParametersExistException(String message, UserDTO userDTO) {
        super(message);
        this.userDTO = userDTO;
    }

    public EditUsersParametersExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EditUsersParametersExistException(Throwable cause) {
        super(cause);
    }
}

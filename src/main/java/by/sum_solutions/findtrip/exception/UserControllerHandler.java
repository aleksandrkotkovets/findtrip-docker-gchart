package by.sum_solutions.findtrip.exception;

import by.sum_solutions.findtrip.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class UserControllerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
        LOGGER.info("SQLException Occured:: URL="+request.getRequestURL());
        return "database_error";
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        LOGGER.error("IOException handler executed");
        //returning 404 error code
    }

}

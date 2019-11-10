package by.sum_solutions.findtrip.exception;

import by.sum_solutions.findtrip.controller.dto.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class UserControllerHandler  {

    @ExceptionHandler(RegistrationParameterIsExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleRegistrationParameterIsExist(RegistrationParameterIsExistException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("apiError", apiError);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

   /* @ExceptionHandler(RegistrationParameterIsExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleEmailExist(RegistrationParameterIsExistException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler( RegistrationParameterIsExistException.class)
    public ResponseEntity<Object> handleLoginExist(RegistrationParameterIsExistException ex, WebRequest request){
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus() );
    }

    @ExceptionHandler(RegistrationParameterIsExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody ApiError handleResourceNotFound(final RegistrationParameterIsExistException exception,
                                                                  final HttpServletRequest request) {
        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.CONFLICT);
        error.setErrors(Collections.singletonList(exception.getLocalizedMessage()));

        return error;
    }*/
}

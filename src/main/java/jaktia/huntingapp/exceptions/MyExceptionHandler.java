package jaktia.huntingapp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.UUID;

@ControllerAdvice // Handles all global exceptions
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @Override // From ResponseEntityExceptionHandler. @ExceptionHandler is not used here because it is not custom exception as in the other exceptions.
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, "Malformed JSON request!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
    @ExceptionHandler(DataNotFoundException.class) // Handles custom exceptions.
    public ResponseEntity<Object> dataNotFound(DataNotFoundException exception){
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError); //HttpStatus.BAD_REQUEST = 400
    }
    @ExceptionHandler(DataDuplicateException.class) // Used to handle custom exceptions.
    public ResponseEntity<Object> dataDuplicate(DataDuplicateException exception){
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError); //HttpStatus.BAD_REQUEST = 400
    }
    @ExceptionHandler(IllegalArgumentException.class) // Used to handle custom exceptions.
    public ResponseEntity<Object> illegalArgument(IllegalArgumentException exception){
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError); //HttpStatus.BAD_REQUEST = 400
    }
    @ExceptionHandler(NotValidPasswordException.class) // Used to handle custom exceptions.
    public ResponseEntity<Object> passwordError(NotValidPasswordException exception){
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError); //HttpStatus.BAD_REQUEST = 400
    }
    @ExceptionHandler(Exception.class) // Global exception for other unhandled exceptions.
    public ResponseEntity<Object> globalException(Exception exception){
        String errorCode = UUID.randomUUID().toString(); // Creates random error code
        APIError apiError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, "internal error! code: (" + errorCode + ")" + " Call support team!");
        // todo: add the application error data into a file (log4J, sl4J)
        System.out.println("INTERNAL_ERROR" + "," + errorCode + "," + exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError); // Internal error = 500
    }

    @Override // This exception is for validations.
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors =  ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            stringBuilder.append(fieldError.getField());
            stringBuilder.append(" ");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append(", ");
        }
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, stringBuilder.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}

package jaktia.huntingapp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class APIError { // This is a DTO class
    private HttpStatus status; //ENUM
    private String statusText;
    private LocalDateTime timestamp;

    public APIError(){
        this.timestamp = LocalDateTime.now();
    }

    public APIError(HttpStatus status, String statusText) {
        this();
        this.status = status;
        this.statusText = statusText;
    }

}

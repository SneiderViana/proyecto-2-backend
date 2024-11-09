package proyecto_2_backend.proyecto_2_backend.security.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AttributeException extends RuntimeException{
    public AttributeException(String message) {
        super(message);
    }
}

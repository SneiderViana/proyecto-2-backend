package proyecto_2_backend.proyecto_2_backend.security.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

     public ResourceNotFoundException(String message){
        super(message);
     }    


}
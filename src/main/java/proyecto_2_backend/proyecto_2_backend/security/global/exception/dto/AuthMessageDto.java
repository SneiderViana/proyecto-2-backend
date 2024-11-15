package proyecto_2_backend.proyecto_2_backend.security.global.exception.dto;

import org.springframework.http.HttpStatus;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthMessageDto {
    
    private String token;
    

}

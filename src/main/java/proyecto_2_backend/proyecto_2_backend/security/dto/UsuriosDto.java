package proyecto_2_backend.proyecto_2_backend.security.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuriosDto {

    @NotBlank(message = "El campo username no puede estar en blanco")
    private String username;

    @NotBlank(message = "El campo correo no puede estar en blanco")
    @Email(message = "Correo invalido ")
    private String correo;
    
    @NotBlank(message = "El campo password no puede estar en blanco")
    private String password;
    //esto es un json
    
    @NotEmpty(message = "Roles obligatorios")
    List<String> roles;
}

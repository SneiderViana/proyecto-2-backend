package proyecto_2_backend.proyecto_2_backend.security.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import proyecto_2_backend.proyecto_2_backend.security.Entity.UsuariosEntity;
import proyecto_2_backend.proyecto_2_backend.security.Service.UsuarioEntityService;
import proyecto_2_backend.proyecto_2_backend.security.dto.UsuriosDto;
import proyecto_2_backend.proyecto_2_backend.security.global.exception.dto.MessageDto;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioEntityService usuarioEntityService;

     @PostMapping("/create")
    public ResponseEntity<MessageDto> create(@Valid @RequestBody UsuriosDto usuriosDto) {
        UsuariosEntity usuariosEntity = usuarioEntityService.create(usuriosDto);
        
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "usuario " + usuariosEntity.getUsername()
        + " ha sido creado"));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getListUser() {
        List<UsuariosEntity> usuarios= this.usuarioEntityService.findall();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

}

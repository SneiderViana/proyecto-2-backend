package proyecto_2_backend.proyecto_2_backend.security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto_2_backend.proyecto_2_backend.security.Service.JwtUtilService;
import proyecto_2_backend.proyecto_2_backend.security.Service.UsuarioEntityService;
import proyecto_2_backend.proyecto_2_backend.security.dto.UsuriosDto;
import proyecto_2_backend.proyecto_2_backend.security.global.exception.dto.AuthMessageDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsuarioEntityService usuarioEntityService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;
      
    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody UsuriosDto usuario){
        

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            usuario.getUsername(), usuario.getPassword()));
            
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(usuario.getUsername());
            
            
            String jwt= this.jwtUtilService.generateTokend(userDetails); 
            AuthMessageDto authMessageDto= new AuthMessageDto();
            authMessageDto.setToken(jwt);

            return new ResponseEntity<AuthMessageDto>(authMessageDto, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de authentication:::" + e.getMessage()); 
        } 
        
    }


}

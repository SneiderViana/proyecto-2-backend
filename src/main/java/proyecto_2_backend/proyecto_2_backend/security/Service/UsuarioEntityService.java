package proyecto_2_backend.proyecto_2_backend.security.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyecto_2_backend.proyecto_2_backend.security.Entity.UsuariosEntity;
import proyecto_2_backend.proyecto_2_backend.security.Repository.UsuarioEntityRepository;
import proyecto_2_backend.proyecto_2_backend.security.dto.UsuriosDto;
import proyecto_2_backend.proyecto_2_backend.security.enums.RolesEnums;
import proyecto_2_backend.proyecto_2_backend.security.global.exception.AttributeException;
import proyecto_2_backend.proyecto_2_backend.security.global.utils.Operations;

@Service
public class UsuarioEntityService {

    @Autowired
    UsuarioEntityRepository usuarioEntityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UsuariosEntity create(UsuriosDto usuariosDto){
        if(usuarioEntityRepository.existsByUsername(usuariosDto.getUsername())){
        throw new AttributeException("Username esta en uso");}

        if(usuarioEntityRepository.existsByCorreo(usuariosDto.getCorreo())){
        throw new AttributeException("Correo esta en uso");} 
        
        return usuarioEntityRepository.save(mapUsuarioFromDto(usuariosDto));
    }

    private UsuariosEntity mapUsuarioFromDto(UsuriosDto usuariosDto){
        int id = Operations.autoIncremento(usuarioEntityRepository.findAll());
        String password= passwordEncoder.encode(usuariosDto.getPassword());
        List<RolesEnums> roles= usuariosDto.getRoles().stream()
        .map(rol -> RolesEnums.valueOf(rol)).collect(Collectors.toList());
        return new UsuariosEntity(id, usuariosDto.getUsername(),
         usuariosDto.getCorreo(), password,roles);
    }

}

package proyecto_2_backend.proyecto_2_backend.security.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proyecto_2_backend.proyecto_2_backend.security.Entity.UsuariosEntity;
import proyecto_2_backend.proyecto_2_backend.security.Repository.UsuarioEntityRepository;

@Service
public class UsuarioDetailServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioEntityRepository usuarioEntityRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuariosEntity> usuarioEntity = usuarioEntityRepository.findByUsernameOrCorreo(username, username);
        if (!usuarioEntity.isPresent()) {
            return null;
        }
        return UsuarioPrincipal.build(usuarioEntity.get());
    }

}

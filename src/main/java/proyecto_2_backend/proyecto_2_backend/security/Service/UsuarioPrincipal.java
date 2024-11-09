package proyecto_2_backend.proyecto_2_backend.security.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import proyecto_2_backend.proyecto_2_backend.security.Entity.UsuariosEntity;

@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails{
   
    private String username;
    private String correo;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioPrincipal build(UsuariosEntity usuarioEntity){
        Collection<GrantedAuthority> authorities =
              usuarioEntity.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuarioEntity.getUsername(), usuarioEntity.getCorreo(), usuarioEntity.getPassword(), authorities);    
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getCorreo() {
        return correo;
    }
 

}

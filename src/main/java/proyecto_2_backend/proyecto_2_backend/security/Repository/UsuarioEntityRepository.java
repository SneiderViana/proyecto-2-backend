package proyecto_2_backend.proyecto_2_backend.security.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import proyecto_2_backend.proyecto_2_backend.security.Entity.UsuariosEntity;

@Repository
public interface UsuarioEntityRepository extends MongoRepository<UsuariosEntity, Integer>{

    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo); 

}

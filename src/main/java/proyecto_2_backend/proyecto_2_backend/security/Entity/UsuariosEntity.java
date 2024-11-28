package proyecto_2_backend.proyecto_2_backend.security.Entity;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import proyecto_2_backend.proyecto_2_backend.security.enums.RolesEnums;
import proyecto_2_backend.proyecto_2_backend.security.global.Entity.EntityId;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "usuarios")
public class UsuariosEntity extends EntityId{

   
    private String username;
    private String correo;
    private String password;
    List<RolesEnums> roles;

    public UsuariosEntity (int id, String username, String correo, String password,List<RolesEnums> roles ){
        this.id = id;
        this.username=username;
        this.password=password;
        this.correo=correo;
        this.roles =roles;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setId(int id){
        super.setId(id);
    }


}

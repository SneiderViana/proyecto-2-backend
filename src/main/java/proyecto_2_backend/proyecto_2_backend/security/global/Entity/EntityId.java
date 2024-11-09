package proyecto_2_backend.proyecto_2_backend.security.global.Entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EntityId {

    @Id
    protected int id;
}

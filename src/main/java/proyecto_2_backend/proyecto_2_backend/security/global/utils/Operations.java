package proyecto_2_backend.proyecto_2_backend.security.global.utils;

import java.util.Comparator;
import java.util.List;
import proyecto_2_backend.proyecto_2_backend.security.global.Entity.EntityId;

public class Operations {

    public static String trimBrackets(String message){return message.replaceAll("[\\[\\]]", "");}

    public static int autoIncremento(List<? extends EntityId> list){
        if (list.isEmpty()) {
            return 1;
        }
        return list.stream().max(Comparator.comparing(EntityId::getId)).get().getId() + 1 ;

    }

}

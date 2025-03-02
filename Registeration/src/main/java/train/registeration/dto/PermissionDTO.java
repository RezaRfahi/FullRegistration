package train.registeration.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PermissionDTO {

    @Id
    long id;
    String name;
    Set<RoleDTO> roles;

}

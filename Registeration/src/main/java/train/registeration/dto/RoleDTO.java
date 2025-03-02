package train.registeration.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class RoleDTO {

    @Id
    long id;
    String name;
    Set<UserDTO> users;
    Set<PermissionDTO> permissions;

}

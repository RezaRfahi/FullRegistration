package train.registeration.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import train.registeration.entity.Role;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class UserDTO {

    @Id
    long id;
    String username;
    String password;
    String email;
    String phone;
    Date registrationDate;
    Date birthDay;
    Set<Role> roles;

}

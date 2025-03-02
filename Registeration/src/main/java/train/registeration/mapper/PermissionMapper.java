package train.registeration.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import train.registeration.dto.UserDTO;
import train.registeration.entity.User;

import java.util.List;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);

    @InheritInverseConfiguration
    User userDTOToUser(UserDTO userDTO);

}

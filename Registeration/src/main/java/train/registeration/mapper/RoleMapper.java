package train.registeration.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import train.registeration.dto.RoleDTO;
import train.registeration.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(Role role);

    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);

    @InheritInverseConfiguration
    Role roleDTOToRole(RoleDTO roleDTO);

}

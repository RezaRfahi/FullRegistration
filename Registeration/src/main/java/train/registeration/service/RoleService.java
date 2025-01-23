package train.registeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.registeration.entity.Permission;
import train.registeration.entity.Role;
import train.registeration.entity.User;
import train.registeration.repository.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    RoleRepository roleRepository;

    public List<Role> findAll()
    {
        assert roleRepository != null;
        return roleRepository.findAll();
    }

    public Role findById(long id)
    {
        return roleRepository.findById(id).orElse(null);
    }

    public Role findByName(String name)
    {
        assert name != null;
        return roleRepository.findByName(name);
    }

    public Set<User> finRoleUsers(long id)
    {
        Role role = roleRepository.findById(id).orElse(null);
        assert role != null;
        return role.getUsers();
    }

    public Role save(String name)
    {
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
        return role;
    }

    public Boolean setUsers(Set<User> users)
    {
        boolean execute = false;
        for(User user : users)
        {
            roleRepository.addUser(user);
            execute = true;
        }

        return execute;

    }

    public Boolean removeUsers(Set<User> users)
    {
        boolean execute = false;
        for(User user : users)
        {
            if (roleRepository.existsUser(user.getId())) {
                roleRepository.removeUser(user);
                execute = true;
            }
        }

        return execute;
    }

    public Boolean setPermissions(Set<Permission> permissions)
    {
        boolean execute = false;
        for (Permission permission : permissions)
        {
            roleRepository.addPermission(permission);
            execute = true;
        }
        return execute;
    }

    public Boolean removePermissions(Set<Permission> permissions)
    {
        boolean execute = false;
        for (Permission permission : permissions)
        {
            roleRepository.removePermission(permission);
            execute = true;
        }
        return execute;
    }

    public boolean update(long id, String name)
    {
        Role role = roleRepository.findById(id).orElse(null);
        assert role != null;
        role.setName(name);
        roleRepository.updateRoleById(id, role);
        return true;
    }

    public Boolean delete(long id)
    {
        roleRepository.deleteById(id);
        return !roleRepository.existsById(id);
    }

}

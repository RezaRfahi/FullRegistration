package train.registeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.registeration.entity.Permission;
import train.registeration.entity.Role;
import train.registeration.repository.PermissionRepository;

import java.util.List;
import java.util.Set;

@Service
public class PermissionService {
    PermissionRepository permissionRepository;

    public List<Permission> findAll()
    {
        assert permissionRepository != null;
        return permissionRepository.findAll();
    }

    public Permission findById(Long id)
    {
        assert permissionRepository != null;
        return permissionRepository.findById(id).orElse(null);
    }

    public Permission findByName(String name)
    {
        assert permissionRepository != null;
        return permissionRepository.findByName(name);
    }

    public Set<Role> findPermissionRole(long id)
    {
        Permission permission = findById(id);
        assert permission != null;
        return permission.getRoles();
    }

    public Permission save(String name)
    {
        Permission permission = new Permission();
        permission.setName(name);
        return permissionRepository.save(permission);
    }

    public Boolean update(long id ,String name)
    {
        Permission permission = findById(id);
        permission.setName(name);
        permissionRepository.updateById(id ,permission);
        return true;
    }

    public Boolean delete(long id)
    {
        permissionRepository.deleteById(id);
        return permissionRepository.findById(id).isEmpty();
    }

}

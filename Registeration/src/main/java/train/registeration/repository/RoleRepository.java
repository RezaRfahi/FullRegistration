package train.registeration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import train.registeration.entity.Permission;
import train.registeration.entity.Role;
import train.registeration.entity.User;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    public Role findByName(String name);

    @Transactional
    public void addUser(User user);

    @Modifying
    @Transactional
    public void removeUser(User user);

    @Transactional
    public void addPermission(Permission permission);

    public void removePermission(Permission permission);

    boolean existsUser(Long id);

    @Modifying
    @Transactional
    void updateRoleById(Long id, Role role);
}
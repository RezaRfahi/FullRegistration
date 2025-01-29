package train.registeration.controller.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import train.registeration.entity.Permission;
import train.registeration.entity.Role;
import train.registeration.entity.User;
import train.registeration.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("roles")
    public ResponseEntity<List<Role>> getRoles()
    {
        try {
            List<Role> roles = roleService.findAll();
            if (roles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }else
                return ResponseEntity.ok(roles);
        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id)
    {
        try {
        Role role = roleService.findById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(role);

        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("roles/users/{id}")
    public ResponseEntity<List<User>> getRoles(@PathVariable int id)
    {
        try {
            Role role = roleService.findById(id);
            if (role == null) {
                return ResponseEntity.notFound().build();
            }

            List<User> users = roleService.finRoleUsers(id).stream().toList();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("roles/permissions/{id}")
    public ResponseEntity<List<Permission>> getPermissions(@PathVariable int id)
    {
        try {
            Role role = roleService.findById(id);
            if (role == null) {
                return ResponseEntity.notFound().build();
            }
            List<Permission> permissions = role.getPermissions().stream().toList();
            if (permissions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(permissions);
        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        try {
            if(roleService.findByName(role.getName()) != null)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            Role created_role = roleService.save(role.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(created_role);

        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("roles/{id}")
    public ResponseEntity<Role> updateRoles(@PathVariable long id, @RequestBody Role role)
    {
        try {
            Role existsrole = roleService.findById(id);
            if (existsrole == null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            existsrole.setName(role.getName());
            existsrole = roleService.save(existsrole.getName());
            return ResponseEntity.ok(existsrole);

        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("roles/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable long id)
    {
        try {

        Role role = roleService.findById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        boolean deleted_role = roleService.delete(id);
        return ResponseEntity.ok(deleted_role ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
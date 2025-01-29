package train.registeration.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import train.registeration.entity.Permission;
import train.registeration.entity.Role;
import train.registeration.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("permissions")
    public ResponseEntity<List<Permission>> getPermissions()
    {
        try {
            List<Permission> permission = permissionService.findAll();
            if (permission.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
                return ResponseEntity.ok(permission);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("permissions/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable("id") Long id)
    {
        try {
            Permission permission = permissionService.findById(id);
            if (permission == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(permission);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("permission/roles/{id}")
    public ResponseEntity<List<Role>> getRoles(@PathVariable("id") Long id)
    {
        try {
            Permission permission = permissionService.findById(id);
            List<Role> roles = permission.getRoles().stream().toList();
            if (roles.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(roles);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("permission")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission)
    {
        try {
                if (permissionService.findByName(permission.getName()) != null)
                {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
                Permission created_permission = permissionService.save(permission.getName());
                return ResponseEntity.status(HttpStatus.CREATED).body(created_permission);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("permission/{id}")
    public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission, long id)
    {
        try {
            Permission updated_permission = permissionService.findById(id);
            if (updated_permission == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            updated_permission.setName(permission.getName());
            updated_permission.setRoles(permission.getRoles());
            updated_permission = permissionService.save(updated_permission.getName());
            return ResponseEntity.ok(updated_permission);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("permission/{id}")
    public ResponseEntity<HttpStatus> deletePermission(@PathVariable("id") long id)
    {
        try {
            Permission permission = permissionService.findById(id);
            if (permission == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            boolean deleted = permissionService.delete(id);
            return ResponseEntity.ok(deleted ? HttpStatus.OK : HttpStatus.CONFLICT);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

}

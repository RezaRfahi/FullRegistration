package train.registeration.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import train.registeration.entity.Role;
import train.registeration.entity.User;
import train.registeration.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers()
    {
     try {

     List<User> users = userService.findAll();

     if (users.isEmpty()) {
         return ResponseEntity.noContent().build();
     }

     return ResponseEntity.ok(users);

     } catch (Exception e) {
         return ResponseEntity.badRequest().build();
     }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id)
    {
        try {

        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("users/roles/{id}")
    public ResponseEntity<List<Role>> getAllRoles(@PathVariable("id") long id)
    {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            List<Role> roles = userService.findAllRoles(id).stream().toList();
            if (roles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(roles);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User created_user = userService.save(user.getUsername(), user.getPassword(), user.getPhone(), user.getBirthday(), user.getEmail());
            if (created_user == null) {
                return ResponseEntity.badRequest().build();
            }else
                return ResponseEntity.ok(created_user);
        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user)
    {
        try {
            int execute = userService.update(id, user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail(), user.getBirthday());
            if (execute == 0) {
                return ResponseEntity.badRequest().build();
            }
            else
                return ResponseEntity.ok(user);
        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id)
    {
        try {
            Boolean execute = userService.delete(id);
            return execute ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

}

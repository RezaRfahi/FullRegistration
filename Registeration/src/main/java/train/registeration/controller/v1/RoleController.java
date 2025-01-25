package train.registeration.controller.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import train.registeration.service.UserService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class RoleController {
    @Autowired
    UserService userService;
}

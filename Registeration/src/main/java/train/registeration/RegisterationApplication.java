package train.registeration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import train.registeration.entity.Permission;
import train.registeration.entity.Role;
import train.registeration.entity.User;

import java.lang.reflect.Method;

@SpringBootApplication
public class RegisterationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterationApplication.class, args);
        System.out.println("Hello World!");
//
//        Method[] methods = User.class.getMethods();
//        for (Method method : methods) {
//            System.out.println(method);
//        }
//
//        System.out.println("Hello World!");
//
//        Method[] methods1 = Role.class.getMethods();
//        for (Method method : methods1) {
//            System.out.println(method);
//        }
//
//        System.out.println("Hello World!");
//
//        Method[] methods2 = Permission.class.getMethods();
//        for (Method method : methods2) {
//            System.out.println(method);
//        }
//
    }

}

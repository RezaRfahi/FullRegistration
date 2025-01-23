package train.registeration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import train.registeration.entity.User;

import java.lang.reflect.Method;

@SpringBootApplication
public class RegisterationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterationApplication.class, args);
        System.out.println("Hello World!");

        Method[] methods = User.class.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

    }

}

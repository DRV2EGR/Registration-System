package RegistrationServ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class ServMain {
    public static void main(String[] args) {
        SpringApplication.run(ServMain.class, args);
        RestController rest = new RestController();
        rest.refresh();
        System.out.println(rest.usersArr.get(0));
    }
}

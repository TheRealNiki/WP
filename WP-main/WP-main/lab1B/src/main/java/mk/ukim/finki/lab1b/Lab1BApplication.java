package mk.ukim.finki.lab1b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Lab1BApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1BApplication.class, args);
    }

}

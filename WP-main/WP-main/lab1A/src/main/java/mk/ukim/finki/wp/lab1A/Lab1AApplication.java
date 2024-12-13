package mk.ukim.finki.wp.lab1A;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Lab1AApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1AApplication.class, args);
    }

}

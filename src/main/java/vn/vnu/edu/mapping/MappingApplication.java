package vn.vnu.edu.mapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MappingApplication.class, args);
    }

}

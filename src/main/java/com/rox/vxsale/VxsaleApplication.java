package com.rox.vxsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VxsaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VxsaleApplication.class, args);
    }

}

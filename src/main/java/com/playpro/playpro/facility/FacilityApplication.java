package com.playpro.playpro.facility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.playpro.playpro")
public class FacilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacilityApplication.class, args);
    }
}

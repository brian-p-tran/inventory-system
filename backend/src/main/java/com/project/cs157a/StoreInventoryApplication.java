package com.project.cs157a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.boot.web.servlet.*;

@SpringBootApplication
@ServletComponentScan
public class StoreInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreInventoryApplication.class, args);
    }
}


package com.xiaoba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xiaoba"})
public class XiaobaSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaobaSearchApplication.class, args);
    }

}

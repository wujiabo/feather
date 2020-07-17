package com.wujiabo.opensource.feather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 项目启动方法
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootStart {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootStart.class, args);
        System.out.println("*******************************************");
    }
}

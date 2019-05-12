package com.yougou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.yougou.mapper")
@ComponentScan(value = "com.yougou.*")
@SpringBootApplication
public class YougouOutsideShoprobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(YougouOutsideShoprobotApplication.class, args);
	}



}


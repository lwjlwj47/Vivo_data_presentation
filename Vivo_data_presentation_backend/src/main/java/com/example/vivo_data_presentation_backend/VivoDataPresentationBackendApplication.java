package com.example.vivo_data_presentation_backend;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.vivo_data_presentation_backend.mapper")
public class VivoDataPresentationBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(VivoDataPresentationBackendApplication.class, args);
		log.info("项目启动成功......");
	}

}

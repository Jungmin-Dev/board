package jungmin.board;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"jungmin.board.mapper"})
@SpringBootApplication(scanBasePackages = {"jungmin.board.config", "jungmin.board.controller" ,"jungmin.board.service" })
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}

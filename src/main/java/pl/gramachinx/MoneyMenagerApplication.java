package pl.gramachinx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan

public class MoneyMenagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyMenagerApplication.class, args);
	}
}

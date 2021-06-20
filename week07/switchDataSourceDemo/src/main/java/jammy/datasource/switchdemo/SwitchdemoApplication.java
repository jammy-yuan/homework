package jammy.datasource.switchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "jammy.datasource.mapper")
public class SwitchdemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SwitchdemoApplication.class, args);
    }

}

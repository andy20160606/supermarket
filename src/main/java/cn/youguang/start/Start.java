package cn.youguang.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Andy丶 on 2017/2/17.
 */


@Configuration()
@EnableAutoConfiguration
@ComponentScan("cn.youguang")
@EnableJpaRepositories("cn.youguang")
@EntityScan("cn.youguang")
@EnableTransactionManagement
public class Start extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Start.class);
    }


}

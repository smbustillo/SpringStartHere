package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import services.CommentService;


@Configuration
@ComponentScan(basePackages = {"proxies","services","repositories","processors","aop"})
@EnableAspectJAutoProxy
public class ProjectConfiguration {
    /*@Bean
    public CommentService2 commentService(){
        return new CommentService2();
    }*/
}

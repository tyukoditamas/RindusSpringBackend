package project.rindus.springBackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonPlaceHolderConfiguration {
    @Bean
    JsonPlaceHolderBean getJsonPlaceHolderConfiguration () {return new JsonPlaceHolderBean();}
}

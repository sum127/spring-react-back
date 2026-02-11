package org.test.mallapi.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ModelMapper를 사용하기위한 config
@Configuration
public class RootConfig {

    @Bean
    public ModelMapper getMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PACKAGE_PRIVATE)
        .setMatchingStrategy(MatchingStrategies.LOOSE);

        return mapper;
    }
}

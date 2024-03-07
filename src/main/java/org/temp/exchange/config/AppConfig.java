package org.temp.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * App Config
 */

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();


        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();

        // MappingJackson2HttpMessageConverter application/octet-stream
        messageConverters.add(new MappingJackson2HttpMessageConverter() {{
            setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON));
        }});

        return restTemplate;
    }
}

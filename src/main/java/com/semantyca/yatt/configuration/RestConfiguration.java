package com.semantyca.yatt.configuration;

import com.semantyca.yatt.util.RequestResponseLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();

     //   restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory()));
     //   restTemplate.setMessageConverters(Collections.singletonList(mappingJacksonHttpMessageConverter()));

        restTemplate.setInterceptors( Collections.singletonList(new RequestResponseLoggingInterceptor()) );


        return restTemplate;
    }

}

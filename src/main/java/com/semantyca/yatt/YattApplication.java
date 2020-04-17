package com.semantyca.yatt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class YattApplication {

	public static void main(String[] args) {
		SpringApplication.run(YattApplication.class, args);
	}

/*	@Bean
	public Module module(){
		SimpleModule module = new SimpleModule();
		module.addSerializer(ZonedDateTime.class, new TaskConverter.Serialize());
		module.addDeserializer(Task.class, new TaskConverter.Deserialize());
		return module;
	}*/

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(1000000);
		return loggingFilter;
	}

}

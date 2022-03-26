package booking.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class RestApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(RestApplication.class);

    public static void main(String[] args) {
	SpringApplication.run(RestApplication.class, args);
	logger.info("BookingRestApplication started");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	return builder.sources(RestApplication.class);
    }

	@Bean
	public Docket controllerAPI() {
		return new Docket(DocumentationType.OAS_30)
			.select()
			.apis(RequestHandlerSelectors.basePackage("booking.rest.controller"))
			.build();
	}
}

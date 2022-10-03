package one.digitalinnovation.shorttermparking.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("one.digitalinnovation.shorttermparking"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Short Term Parking API")
                .description("API to manage rotating parking spaces")
                .version("1.0.0")
                .license("MIT License")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .contact(new Contact(
                        "Sandro Smarzaro",
                        "https://www.linkedin.com/in/sandrosmarzaro/",
                        "sansmarzaro@gmail.com"
                ))
                .build();
    }
}

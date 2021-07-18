package com.driva.drivaapi.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.Collections;
import java.util.List;

import static com.driva.drivaapi.constant.SwaggerConstant.API_DESCRIPTION;
import static com.driva.drivaapi.constant.SwaggerConstant.API_TITLE;
import static com.driva.drivaapi.constant.SwaggerConstant.API_VERSION;
import static com.driva.drivaapi.constant.SwaggerConstant.AUTHORIZATION_DESCRIPTION;
import static com.driva.drivaapi.constant.SwaggerConstant.AUTHORIZATION_SCOPE;
import static com.driva.drivaapi.constant.SwaggerConstant.CONTACT_EMAIL;
import static com.driva.drivaapi.constant.SwaggerConstant.CONTACT_NAME;
import static com.driva.drivaapi.constant.SwaggerConstant.CONTACT_URL;
import static com.driva.drivaapi.constant.SwaggerConstant.LICENSE;
import static com.driva.drivaapi.constant.SwaggerConstant.LICENSE_URL;
import static com.driva.drivaapi.constant.SwaggerConstant.SECURE_PATH;
import static com.driva.drivaapi.constant.SwaggerConstant.SECURITY_REFERENCE;
import static com.driva.drivaapi.constant.SwaggerConstant.TERM_OF_SERVICE;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(SWAGGER_2).apiInfo(apiInfo())
                .forCodeGeneration(true)
                .securityContexts(singletonList(securityContext()))
                .securitySchemes(singletonList(apiKey()))
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex(SECURE_PATH))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(API_TITLE, API_DESCRIPTION, API_VERSION, TERM_OF_SERVICE, contact(),
                LICENSE, LICENSE_URL, Collections.emptyList());
    }

    private Contact contact() {
        return new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
    }

    private ApiKey apiKey() {
        return new ApiKey(SECURITY_REFERENCE, AUTHORIZATION, SecurityScheme.In.HEADER.name());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(securityReference()).build();
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope[] authorizationScope = {new AuthorizationScope(AUTHORIZATION_SCOPE, AUTHORIZATION_DESCRIPTION)};
        return singletonList(new SecurityReference(SECURITY_REFERENCE, authorizationScope));
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("test-app-client-id")
                .clientSecret("test-app-client-secret")
                .realm("test-app-realm")
                .appName("test-app")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .enableCsrfSupport(false)
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.LIST)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .showCommonExtensions(true)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}


package com.sapient.trg.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//https://springfox.github.io/springfox/docs/snapshot/

@Component
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {
  private final String baseUrl;

  public SwaggerUiWebMvcConfigurer(
      @Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
    registry.
        addResourceHandler(baseUrl + "/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .resourceChain(false);
  }

  //http://localhost:8082/usm/swagger-ui/index.html
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController(baseUrl + "/swagger-ui/")
        .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/usm/Userprofile")
        .allowedOrigins("http://editor.swagger.io");
    registry
    .addMapping("/usm/region")
    .allowedOrigins("http://editor.swagger.io");
    registry
    .addMapping("/usm/role")
    .allowedOrigins("http://editor.swagger.io");
    registry
    .addMapping("/usm/Credential")
    .allowedOrigins("http://editor.swagger.io");
    registry
        .addMapping("/usm/api-docs.*")
        .allowedOrigins("http://editor.swagger.io");
   
  }
}

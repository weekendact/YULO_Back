package inhatc.yulo.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/board/images/**") // --1
                .addResourceLocations("file:////Users/hongseongmin/GitHub/YULO_Back/src/main/resources/static/"); //--2
    }
}

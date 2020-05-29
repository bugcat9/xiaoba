package com.xiaoba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author zhouning
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这个将应用到url中
        registry.addResourceHandler("/images/**")
                //这里填的是图片的绝对父路径
                .addResourceLocations("file:/home/images/");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // addPathPatterns表示对请求进行拦截，excludePathPatterns表示除了()请求之外
//        registry.addInterceptor(new LoginHandlerIntercep()).addPathPatterns("/**").
//                excludePathPatterns("/index.html","/","/login");
//
//    }
}

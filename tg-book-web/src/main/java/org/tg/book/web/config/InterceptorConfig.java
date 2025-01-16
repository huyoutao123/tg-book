package org.tg.book.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tg.book.web.interceptor.AuthInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己的拦截器并设置拦截的请求路径
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                // 排除的请求路径
                .excludePathPatterns("/auth/login");
    }
}

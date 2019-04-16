package com.owl.owlBlog.interceptor;

import com.owl.owlBlog.util.TaleUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
/**
 * 向mvc中添加自定义组件
 * Created by BlueT on 2017/3/9.
 */
@Component
public class WebMvcConfigInterceptor implements WebMvcConfigurer {
    @Resource
    private BaseInterceptor baseInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源  implements WebMvcConfigurer
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:"+ TaleUtils.getUploadFilePath()+"upload/");

    }
}

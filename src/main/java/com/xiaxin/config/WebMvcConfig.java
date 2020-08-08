package com.xiaxin.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UnderlineToCamelArgumentResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	//1.定义一个convert转换消息对象--字符串解析器
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        //2.将convert添加到converters中并设置优先级
        converters.add(0,converter);

        //1.定义一个convert转换消息对象--json解析器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2.1添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializeConfig serializeConfig=new SerializeConfig();
        //序列化驼峰转下划线
        serializeConfig.propertyNamingStrategy= PropertyNamingStrategy.SnakeCase;
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                //yyyy-MM-dd HH:mm:ss
                SerializerFeature.WriteDateUseDateFormat);

        //2.2处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4.将convert添加到converters中并设置优先级
        converters.add(1,fastConverter);
    }

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }


}
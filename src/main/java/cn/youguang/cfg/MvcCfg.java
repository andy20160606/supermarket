package cn.youguang.cfg;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;


///**
// * springmvc 的个性化配置，可以在这里进行异常处理 比如：shiro未授权的异常处理
// */
@Configuration
public class MvcCfg {

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r =
                new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("UnauthorizedException", "/unauth");
        mappings.setProperty("IOException", "/ioexception");
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("ex");     // Default is "exception"
        r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }
//

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }




//
//    @Bean
//    public HttpMessageConverters HttpMessageConverters() {
//        MappingJackson2HttpMessageConverter mjhmc = new MappingJackson2HttpMessageConverter();
//        List<MediaType> mts = new ArrayList<>();
//        mts.add(MediaType.TEXT_PLAIN);
//        mjhmc.setSupportedMediaTypes(mts);
//        return new HttpMessageConverters(mjhmc);
//    }

//
//    @Bean(name = "commonsMultipartResolver")
//    public CommonsMultipartResolver createMultipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("utf-8");
//        resolver.setMaxUploadSize(409600);
//        return resolver;
//    }


}



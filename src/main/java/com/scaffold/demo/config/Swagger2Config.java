package com.scaffold.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/7 11:13
 * desc   :
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger2.enable}")
    private boolean enable;


    //api接口包扫描路径
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.scaffold.demo";

    public static final String VERSION = "1.0.0";

    @Bean
    public Docket createRestApi() {
        /**
         * 这是为了我们在用 swagger 测试接口的时候添加头部信息
         */
        List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder refreshTokenPar = new ParameterBuilder();
        tokenPar.name("authorization").description("swagger测试用(模拟token传入)非必填 header").modelRef(new ModelRef("string")).parameterType("header").required(false);
        refreshTokenPar.name("refresh_token").description("swagger测试用(模拟刷新token传入)非必填 header").modelRef(new ModelRef("string")).parameterType("header").required(false);
        /**
         * 多个的时候 就直接添加到 pars 就可以了
         */
        pars.add(tokenPar.build());
        pars.add(refreshTokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .enable(enable)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Yiie接口文档") //设置文档的标题
                .description("后台管理系统") // 设置文档的描述
                .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
                .termsOfServiceUrl("http://www.baidu.com") // 设置文档的License信息->1.3 License information
                .build();
    }
}

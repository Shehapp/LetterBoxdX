package com.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;


@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com")
@PropertySource("classpath:mail.properties")
public class AppConfig implements WebMvcConfigurer {



    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    Environment environment;

    @Bean("viewResolver")
    InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver= new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/UrlToReach/**").
                addResourceLocations("/WEB-INF/resources/");
    }


    // those 3 methods add message source
    @Bean
    public MessageSource messageSource() {
        // load property file
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        return messageSource;
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    @Override
    public Validator getValidator() {
        return validator();
    }



    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();


        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(getIntProperty("mail.port"));
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));
        mailSender.setJavaMailProperties(getProperties());

        return mailSender;
    }
    public Properties getProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }
    int getIntProperty(String property){
        String ans = environment.getProperty(property);
        assert ans != null;
        return Integer.parseInt(ans);
    }

}

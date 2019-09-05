package com.megatravel.poruka.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/services/*");
    }

    @Bean(name = "messages")
    public DefaultWsdl11Definition defaultWsdl11DefinitionMessages(XsdSchema messagesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("MessagesPort");
        wsdl11Definition.setLocationUri("/services");
        wsdl11Definition.setTargetNamespace("http://www.megatravel.com/poruka/soap");
        wsdl11Definition.setSchema(messagesSchema);
        return wsdl11Definition;
    }

    @Bean(name = "messagesSchema")
    public XsdSchema messagesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("messages.xsd"));
    }
    
}
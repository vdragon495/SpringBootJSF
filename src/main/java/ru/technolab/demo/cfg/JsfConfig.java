package ru.technolab.demo.cfg;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class JsfConfig extends SpringBootServletInitializer implements ServletContextAware {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        
    	servletContext.addListener("org.springframework.web.context.ContextLoaderListener");
    	servletContext.addListener("org.springframework.web.context.request.RequestContextListener");
    	servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8"));
    	
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        servletContext.setInitParameter("primefaces.FONT_AWESOME", "true");
    }
    
    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
    }

    @Bean
    public ServletRegistrationBean facesServlet() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean registration = new ServletRegistrationBean(servlet, "*.xhtml");
        registration.setName("FacesServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
}

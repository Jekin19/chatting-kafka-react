package chatting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class Application  {
    public static void main(String[] args) throws Exception {
        new SpringApplication(Application.class).run(args);
    }

    // Cors filters to access from other origin
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException { }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletResponse response = (HttpServletResponse) servletResponse;

                // Change this if you are using some other url for client app.
                response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, *");
                response.setHeader("Access-Control-Allow-Credentials", "true");

                if("Options".equals(((HttpServletRequest)servletRequest).getMethod())) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }

            }

            @Override
            public void destroy() { }
        });
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return  registrationBean;
    }
}
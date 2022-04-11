package com.example.search.config;

import lombok.Data;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.search.filter.CorelationIdFromThread;
import com.example.search.filter.CorelationIdFromHeader;

import javax.servlet.*;
import java.io.IOException;


@Data
@Configuration
public class FilterConfig implements Filter {
    @Bean
    public FilterRegistrationBean<FilterConfig> servletRegistrationBean() {
        final FilterRegistrationBean<FilterConfig> registrationBean = new FilterRegistrationBean<>();
        final CorelationIdFromHeader filter = new CorelationIdFromHeader();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

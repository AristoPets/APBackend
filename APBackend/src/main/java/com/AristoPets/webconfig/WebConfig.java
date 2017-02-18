package com.AristoPets.webconfig;


import com.AristoPets.webconfig.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class WebConfig{

    private AuthFilter authFilter;

    @Autowired
    public WebConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public FilterRegistrationBean authenticationFilter(){
        FilterRegistrationBean authentication = new FilterRegistrationBean(authFilter);
        authentication.setName("authenticationFilter");
        ArrayList<String> filterUrls = new ArrayList<>(Arrays.asList(URLS.ALL_STATIC_URLS));
        filterUrls.addAll(Arrays.asList(URLS.API.API_FILTER));
        authentication.setUrlPatterns(filterUrls);
        return authentication;
    }
}

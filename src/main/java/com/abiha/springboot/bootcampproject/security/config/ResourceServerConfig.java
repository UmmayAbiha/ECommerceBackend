package com.abiha.springboot.bootcampproject.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "bootcamp-project";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().mvcMatchers(HttpMethod.POST,"/userRegister","/forgot-password").permitAll().

                mvcMatchers(HttpMethod.PATCH,"/activateUser/{id}","/deactivateUser/{id}").hasRole("ADMIN").

                mvcMatchers("/logout").permitAll().

                mvcMatchers(HttpMethod.GET,"/viewProfile").hasAnyRole("CUSTOMER","SELLER").

                mvcMatchers(HttpMethod.PATCH,"/updateProfileDetails","/updatePassword","/updateAddress/{id}").hasRole("SELLER").

                mvcMatchers(HttpMethod.GET,"/viewAddress").hasRole("CUSTOMER").
                mvcMatchers(HttpMethod.POST,"/add-address").hasRole("CUSTOMER").
                mvcMatchers(HttpMethod.PATCH,"/update-customer-profile","/update-customer-password","/updateCustomerAddress/{id}").hasRole("CUSTOMER").
                mvcMatchers(HttpMethod.DELETE,"/deleteCustomerAddress/{id}").hasRole("CUSTOMER").

                mvcMatchers(HttpMethod.GET,"/customers","/sellers").hasRole("ADMIN").

                mvcMatchers("/confirm-account","/login","/resent-activation-link","/reset-password").permitAll().and().csrf().disable().logout().logoutSuccessUrl("/logout/successfully");
    }
}
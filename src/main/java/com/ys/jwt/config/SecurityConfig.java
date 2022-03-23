package com.ys.jwt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity   //기본적인 web보안을 활성화
// 추가설정을 위해 WebSecurityConfigurer를 implements 하거나 WebSecurityConfigurerAdapter를 extends함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**"
                            ,"/swagger-ui.html/**" ,"/swagger-resources/**"  ,"/webjars/**", "/v2/api-docs/**"
                            ,"/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()         // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다
                .antMatchers("/api/hello").permitAll()        // 인증없이 접근을 허용
                .anyRequest().authenticated();       // 나머지 요청들은 모두 인증되어야함
    }
}

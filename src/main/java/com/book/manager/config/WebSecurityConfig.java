package com.book.manager.config;

import com.book.manager.service.UserService;
import com.book.manager.util.R;
import com.book.manager.util.http.CodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Description web security 安全配置
 * @Date 2020/7/21 15:19
 * @Author by Tuple
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权
        http.formLogin()
                //自定义登陆页面
                .loginPage("/login")
                //如果URL为loginPage,则用SpringSecurity中自带的过滤器去处理该请求
                .successForwardUrl("/index")
                .loginProcessingUrl("/new_login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(R.success(CodeEnum.SUCCESS)));
                        writer.flush();
                        writer.close();
                    }
                })
                .and()
                //请求授权
                .authorizeRequests()
                //在访问我们的URL时，我们是不需要省份认证，可以立即访问
                .antMatchers("/javaex/**","/","/favicon.ico","/login","/user/login", "/new_login", "/upLoad", "/images/**").permitAll()
                //所有请求都被拦截，都需认证
                .anyRequest().authenticated()
                .and()
                // 请求头允许X-ContentType-Options
                //.headers().contentTypeOptions().disable()
                //.and()
                // 请求头允许X-Frame-Options, 否则所有iframe将失效
                .headers().frameOptions().disable()
                // 注销, 回到首页
//                .logout().logoutSuccessUrl("/")
                //SpringSecurity保护机制
                .and()
                .csrf().disable();

        // 开启记住我功能
//        http.rememberMe().rememberMeParameter("remember");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 认证
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // swagger 资源放行
        web.ignoring().antMatchers("/webjars/**","/v2/**","/swagger-resources/**","/doc.html","/docs.html","swagger-ui.html");
    }

    /**
     * 指定加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }
}

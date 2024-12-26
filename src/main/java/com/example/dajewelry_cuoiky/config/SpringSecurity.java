package com.example.dajewelry_cuoiky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF
                .authorizeHttpRequests(auth -> auth
                        // Cho phép truy cập các trang mà không cần đăng nhập
                        .requestMatchers(
                                "/register/**",
                                "/home/**",
                                "/",
                                "/api/products/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/php/**",
                                "/webfonts/**"
                        ).permitAll()
                        // Yêu cầu đăng nhập cho các trang admin
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ ADMIN được phép truy cập
                        .requestMatchers("/users").hasRole("ADMIN")   // Chỉ ADMIN được phép truy cập
                        .anyRequest().authenticated() // Các trang khác yêu cầu đăng nhập
                )
                // Cấu hình trang đăng nhập
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                // Cấu hình đăng xuất
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true) // Xóa session khi đăng xuất
                        .deleteCookies("JSESSIONID") // Xóa cookie
                        .permitAll()
                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}

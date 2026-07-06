//package app.escuela.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.controller.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.controller.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/docs/login", "/css/**", "/js/**", "/webjars/**", "/images/**").permitAll()
//                        .requestMatchers("/editar/**", "/agregar/**", "/eliminar/**").hasRole("ADMIN")
//                        .requestMatchers("/", "/inicio", "/docente").hasAnyRole("USER", "ADMIN") // 🔥 FIX
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/docs/login")
//                        .defaultSuccessUrl("/inicio", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/docs/login?logout")
//                )
//                .exceptionHandling(ex -> ex
//                        .accessDeniedPage("/errores/403")
//                );
//
//        return http.build();
//    }
//}
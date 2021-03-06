package fr.ktheo.back.security;

import fr.ktheo.back.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authTokenFilter(){
        return new AuthTokenFilter();
    }

    @Configuration
//    @Order(1)
    public class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .cors()
                    .and().csrf().ignoringAntMatchers("/api/**")

                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers("/api/userdata/**").permitAll()
                    .antMatchers("/api/profil/**").permitAll()
                    .antMatchers("/api/user/**").permitAll()
                    .antMatchers("/api/{id}/**").permitAll()
                    .antMatchers("/api/artwork/**").permitAll()
                    .antMatchers("/api/report/**").permitAll()
                    .antMatchers("/api/bid/**").permitAll()
                    .antMatchers("/api/auction/**").permitAll()
                    .antMatchers("/api/tag/**").permitAll()
                    .antMatchers("/api/comment/**").permitAll()
                    .antMatchers("/api/asset/**").permitAll()
                    .antMatchers("/api/transaction/**").permitAll()
                    .antMatchers("/api/status/**").permitAll()
                    .antMatchers("/api/category/**").permitAll()
                    .antMatchers("/api/comment/**").permitAll()
                    //.antMatchers("/api/user/**").authenticated()
                    .anyRequest().authenticated();
            http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }


//    @Configuration
//    @Order(2)
//    public class FormLoginWebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth
//                    .userDetailsService(userDetailsService)
//                    .passwordEncoder(passwordEncoder());
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .antMatchers("/api/**").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/signin")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .permitAll()
//                    .invalidateHttpSession(true).clearAuthentication(true)
//                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//
//        }
//    }

}

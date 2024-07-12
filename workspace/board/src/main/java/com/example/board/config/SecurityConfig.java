package com.example.board.config;

import com.example.board.domain.dto.UsersDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.mapper.UsersMapper;
import com.example.board.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity // 웹 보안 활성화, Spring Security
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final UsersMapper usersMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 전체 요청에 접근할 수 있도록 하는 코드
//        return http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//                .build();


        return http
                // cross - site request forgery : 삭제 에러 잡기 - sql보호벽 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // 요청에 대한 인증 밎 인가를 설정
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // 로그인을 OAuth 기반으로 구성할 것이다
                .oauth2Login(login -> login
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        ).successHandler(authenticationSuccessHandler())
                )


                .logout(logout -> logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            request.getSession().invalidate();
                            //
//                            String ClientId = request.getParameter("ClientId");
                            String clientId = "40313f0aad549e574230cefb17db5a91";
                            String logoutRedirectUri = "http://localhost:8090";
                            String logoutUri = "https://kauth.kakao.com/oauth/logout?client_id=" + clientId + "&logout_redirect_uri=" + logoutRedirectUri;
                            response.sendRedirect(logoutUri);
                        })
                )
                
                .build();

    }


    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (request, response, auth) -> {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) auth.getPrincipal();

            UsersDTO user = usersMapper.findByProviderId(customOAuth2User.getProviderId());

            if(user.getRole().equals("new")){
                response.sendRedirect( "/board/join");
            }
            else {
                response.sendRedirect( "/board/list");
            }

        };
    }


}

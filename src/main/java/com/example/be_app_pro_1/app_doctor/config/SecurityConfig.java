package com.example.be_app_pro_1.app_doctor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.example.be_app_pro_1.app_doctor.component.JwtAuthenticationFilter;
import static com.example.be_app_pro_1.app_doctor.enums.Role.ADMIN;
import static com.example.be_app_pro_1.app_doctor.enums.Role.MANAGER;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.ADMIN_CREATE;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.ADMIN_DELETE;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.ADMIN_READ;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.ADMIN_UPDATE;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.MANAGER_CREATE;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.MANAGER_DELETE;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.MANAGER_READ;
import static com.example.be_app_pro_1.app_doctor.enums.Permission.MANAGER_UPDATE;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant.ApiPath;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

	private static final String[] WHITE_LIST_URL = {
			ApiPath.API + ApiPath.AUTH + ApiPath.REGISTER,
			ApiPath.API + ApiPath.AUTH + ApiPath.LOGIN,
			ApiPath.API + ApiPath.AUTH + ApiPath.TEST,
			ApiPath.TEST,
			};
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	private final AccessDeniedHandler accessDeniedHandler;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req ->
						req.requestMatchers(WHITE_LIST_URL).permitAll()
//								.requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
//								.requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
//								.requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
//								.requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
//								.requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
								.anyRequest().authenticated()
				)
//				.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider)
				.exceptionHandling(eh -> {
					eh.authenticationEntryPoint(authenticationEntryPoint);
					eh.accessDeniedHandler(accessDeniedHandler);
				})
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//				.logout(logout ->
//						logout.logoutUrl("/api/v1/auth/logout")
//								.addLogoutHandler(logoutHandler)
//								.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//				);
		return http.build();
	}

}

package ayu.edu.tr.iskolik.infrastructure.configuration;

import ayu.edu.tr.iskolik.infrastructure.interceptor.LogExecutionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		var source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/i18n/messages");
		source.setUseCodeAsDefaultMessage(true);
		source.setDefaultEncoding("UTF-8");
		return source;
	}

	/**
	 * Deals with locale resolution required when localizing web applications to specific locales.
	 * Spring ships with a few LocaleResolver implementations that may come in handy in various scenarios:
	 * FixedLocaleResolver: Always resolves the locale to a singular fixed language. Mostly used for debugging purposes.
	 * AcceptHeaderLocaleResolver: Resolves the locale using an “accept-language” HTTP header retrieved from an HTTP request.
	 * SessionLocaleResolver: Resolves the locale and stores it in the HttpSession of the user.
	 * CookieLocaleResolver: Resolves the locale and stores it in a cookie stored on the user’s machine. The resolved locale data will last even between sessions as long as browser cookies aren’t cleared by the user.
	 */
	// Farklı dil desteğine gerek olmadığı için kaldırıldı
	/*
	@Bean
	public LocaleResolver localeResolver() {
		var sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(new Locale("tr", "TR"));
		return sessionLocaleResolver;
	}
	 */

	/**
	 * Switches to a new locale based on the value of the "lang" parameter appended to a request.
	 * Intercepts each request that the application receives and eagerly checks for a parameter having the specified name
	 * on the HTTP request. If found, the interceptor uses the registered localeResolver bean as the current user’s locale.
	 */
	// Farklı dil desteğine gerek olmadığı için kaldırıldı
	/*
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		var localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	 */

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogExecutionInterceptor());
		// registry.addInterceptor(localeChangeInterceptor());
	}
}

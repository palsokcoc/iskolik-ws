package ayu.edu.tr.iskolik.infrastructure.localization;

import ayu.edu.tr.iskolik.infrastructure.interceptor.RestControllerExceptionHandler;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageLocalizationService {

	private static final Logger logger = LoggerFactory.getLogger(MessageLocalizationService.class);

	private static ReloadableResourceBundleMessageSource messageSource;

	public MessageLocalizationService(ReloadableResourceBundleMessageSource messageSource) {
		MessageLocalizationService.messageSource = messageSource;
	}

	public static String getLocaleMessage(String key, Object... args) {
		Locale locale = null;
		try {
			locale = getLocale();
			logger.info("MessageLocalizationService/getLocaleMessage"," locale={} key={} ",locale.toString(),key);
			return messageSource.getMessage(key, args, locale);
		} catch (NoSuchMessageException e) {
			logger.error("MessageLocalizationService/getLocaleMessage", "HATA ALINDI", e);
			// log level is error, because every publicly reachable api error messages must be clear
			// a missing message must be fixed immediately.
			return messageSource.getMessage("message.key.not.found", args, locale);
		}
	}

	private static Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}
}

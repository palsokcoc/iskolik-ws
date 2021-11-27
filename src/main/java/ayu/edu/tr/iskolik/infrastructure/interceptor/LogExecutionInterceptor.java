package ayu.edu.tr.iskolik.infrastructure.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class LogExecutionInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LogExecutionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				request.setAttribute("requestStartTime", System.currentTimeMillis());
				logger.info("preHandle"," Starting rest method for  {}", handlerMethod.getBean().getClass().getName());
			}
		} catch (Exception e) {
			logger.error("Caught an exception while executing handler method", "HATA ALINDI", e);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				logger.info("afterCompletion ","Completed rest method for {} takes {} ms", handlerMethod.getBean().getClass().getName(),
						(System.currentTimeMillis() - (Long) request.getAttribute("requestStartTime")));
			}
		} catch (Exception e) {
			logger.error("Caught an exception while executing handler method", "HATA ALINDI", e);
		}
	}
}

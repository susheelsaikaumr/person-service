package com.centime.person.infrastructure.web;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Custom implementation of {@link org.springframework.web.servlet.AsyncHandlerInterceptor} to log the requests that are
 * coming into the Service. This class also creates a Unique Id per {@link HttpServletRequest} for better traceability.
 *
 * @author Susheel
 */
@Slf4j
class LogRequestInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME_KEY = "startTime";

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(final HttpServletRequest request,final HttpServletResponse response,final Object handler) {

        long startTime = System.currentTimeMillis();

        log.info("{} Request {} received from {} .", request.getMethod(),
                request.getRequestURL().toString(), request.getRemoteHost());
        logRequestParams(request);
        request.setAttribute(START_TIME_KEY, startTime);
        return true;
    }

    /**
     * Log the Request Params
     *
     * @param request HttpServletRequest
     */
    private void logRequestParams(HttpServletRequest request) {

        Enumeration<?> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            log.info("Parameter Name - {}, Value - {}", paramName, request.getParameter(paramName));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(final HttpServletRequest request,final HttpServletResponse response,final Object handler,
                                final Exception ex) {

        long startTime = (Long) request.getAttribute(START_TIME_KEY);
        log.info("Request {} from {} processed in {} ms.", request.getRequestURL().toString(), request.getRemoteAddr(),
                System.currentTimeMillis() - startTime);
    }

}

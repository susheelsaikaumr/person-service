package com.centime.person.infrastructure.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Interceptor to initialize the RequestContext for every Http Request and clear it after processing.
 *
 * @author Susheel
 */
class RequestContextInterceptor extends HandlerInterceptorAdapter {

    /*
     * (non-Javadoc)
     *
     * @see org.spring framework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (StringUtils.isNotBlank(request.getHeader(RequestContext.CORRELATION_ID_KEY.value()))) {
            RequestContext.put(RequestContext.CORRELATION_ID_KEY,
                    request.getHeader(RequestContext.CORRELATION_ID_KEY.value()));
        }else {
            RequestContext.put(RequestContext.CORRELATION_ID_KEY,
                    UUID.randomUUID().toString());
        }

        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.spring framework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        RequestContext.clearThreadLocalContext();
    }
}

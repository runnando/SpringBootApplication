package com.example.search.filter;

import com.example.search.controller.SearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CorelationIdFromHeader extends com.example.search.config.FilterConfig implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String currentCorrId = httpServletRequest.getHeader(CorelationIdFromThread.CO_RELATION_ID);

        if (!currentRequestIsAsyncDispatcher(httpServletRequest)) {
            if (currentCorrId == null) {
                currentCorrId = UUID.randomUUID().toString();
                logger.info("No correlationId found in Header. Generated : " + currentCorrId);
            } else {
                logger.info("Found correlationId in Header : " + currentCorrId);
            }

            CorelationIdFromThread.setId(currentCorrId);
        }

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    private boolean currentRequestIsAsyncDispatcher(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getDispatcherType().equals(DispatcherType.ASYNC);
    }


    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}

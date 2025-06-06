package com.siscon.employee_service.infrastructure.adapter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class RequestHeaderLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestHeaderLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            logger.info("Headers:");
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                String value = request.getHeader(header);
                logger.info("   {}: {}", header, value);
            }
        }

        filterChain.doFilter(request, response);
    }
}

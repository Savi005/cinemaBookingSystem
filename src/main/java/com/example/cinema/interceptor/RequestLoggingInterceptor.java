package com.example.cinema.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        String requestId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        request.setAttribute("requestId", requestId);
        request.setAttribute("startTime", startTime);

        System.out.println(
                "requestId=" + requestId +
                " method=" + request.getMethod() +
                " endpoint=" + request.getRequestURI() +
                " event=request_started"
        );

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

        String requestId = (String) request.getAttribute("requestId");
        long startTime = (long) request.getAttribute("startTime");

        long duration = System.currentTimeMillis() - startTime;

        System.out.println(
                "requestId=" + requestId +
                " status=" + response.getStatus() +
                " duration=" + duration + "ms" +
                " event=request_completed"
        );
    }
}
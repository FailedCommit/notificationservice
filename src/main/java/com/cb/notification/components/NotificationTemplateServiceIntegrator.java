package com.cb.notification.components;

import com.cb.notification.beans.dtos.NotificationTemplateRequest;
import com.cb.notification.beans.dtos.NotificationTemplateResponse;
import com.cb.notification.controllers.NotificationServiceApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@AllArgsConstructor
public class NotificationTemplateServiceIntegrator {
    final Logger logger = LoggerFactory.getLogger(NotificationTemplateServiceIntegrator.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Bulkhead(name = "bulkheadService", fallbackMethod = "bulkheadFallback")
    @CircuitBreaker(name = "circuitBreakerService", fallbackMethod = "circuitBreakerFallback")
    @Retry(name = "retryService", fallbackMethod = "retryFallback")
    @RateLimiter(name = "rateLimiterService", fallbackMethod = "rateLimiterFallback")
    public NotificationTemplateResponse getNotificationTemplate(NotificationTemplateRequest request) {
        NotificationTemplateResponse notificationTemplateResponse = null;
        try {
            notificationTemplateResponse = restTemplate.postForObject(
                    "http://localhost:9093/notification-templates/template", objectMapper.writeValueAsString(request), NotificationTemplateResponse.class);

        } catch (JsonProcessingException e) {
            logger.error("ObjectMapper failed to parse {}, with exception {}", request, e.getStackTrace());
            throw new RuntimeException(e);
        }
        return notificationTemplateResponse;
    }

    public NotificationTemplateResponse bulkheadFallback(NotificationTemplateRequest request, Throwable throwable) {
        NotificationTemplateResponse response = new NotificationTemplateResponse();
        response.setStatus("WARNING");
        response.setStatusDescription("Bulk Head for Notification Template Formatter Service");
        return response;
    }

    public NotificationTemplateResponse circuitBreakerFallback(NotificationTemplateRequest request, Throwable throwable) {
        NotificationTemplateResponse response = new NotificationTemplateResponse();
        response.setStatus("WARNING");
        response.setStatusDescription("Circuit Breaker Opened for Notification Template Formatter Service");
        return response;
    }

    public NotificationTemplateResponse retryFallback(NotificationTemplateRequest request, Throwable throwable) {
        NotificationTemplateResponse response = new NotificationTemplateResponse();
        response.setStatus("WARNING");
        response.setStatusDescription("Retry Failed for Notification Gateway Service");
        return response;
    }

    public NotificationTemplateResponse rateLimiterFallback(NotificationTemplateRequest request, Throwable throwable) {
        NotificationTemplateResponse response = new NotificationTemplateResponse();
        response.setStatus("WARNING");
        response.setStatusDescription("Rate Limiter Failed for Notification Template Formatter Service");
        return response;
    }
}

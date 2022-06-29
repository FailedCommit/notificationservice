package com.cb.notification;

import com.cb.notification.beans.CbConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@SpringBootApplication
public class NotificationserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationserviceApplication.class, args);
    }

//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder()
//                .baseUrl("http://localhost:9092")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
//                .build();
//    }

//    @Bean
//    public WebClient templateServiceWebClient() {
//        return WebClient.builder()
//                .baseUrl("http://localhost:9093/notification-templates/template")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
//                .build();
//    }

    @Bean
    public CbConfig mailConfigs() {
        // Notification Preference service
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "http://localhost:9092/configs/type/COMMUNICATION";
        ResponseEntity<CbConfig> response
                = restTemplate.getForEntity(resourceUrl, CbConfig.class);

        return response.getBody();
    }

    @Bean
    public RestTemplate templateServiceClient() {
        return new RestTemplateBuilder()
                .defaultHeader("Content-Type", "application/json")
                .rootUri("http://localhost:9093/notification-templates/template")
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        CbConfig cbConfig = mailConfigs();
        CbConfig.GmailGatewayConfig mailConfig = cbConfig.getGmailGatewayConfig();
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol(mailConfig.getMailProtocol());
        javaMailSender.setHost(mailConfig.getMailHost());
        javaMailSender.setPort(Integer.parseInt(mailConfig.getMailPort()));
        javaMailSender.setUsername(mailConfig.getSenderEmail());
        javaMailSender.setPassword(mailConfig.getSendersEmailPassword());
        javaMailSender.setPassword("ybaoautdzbgotdoi");
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", mailConfig.isMailSmtpAuth());
        props.put("mail.smtp.starttls.enable", mailConfig.isSmtpStartTLS());
        props.put("mail.debug", "true");
        return javaMailSender;
    }
}


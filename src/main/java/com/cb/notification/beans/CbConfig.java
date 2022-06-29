package com.cb.notification.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbConfig {
    private String id;
    private String type;
    private TwilioSmsGatewayConfiguration twilioSMSGatewayConfig;
    private GmailGatewayConfig gmailGatewayConfig;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class TwilioSmsGatewayConfiguration {
        private String twilioUserName;
        private String twilioPassword;
        private String smsFrom;
        private String customerMobile; // Should be coming from UserService not config service
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class GmailGatewayConfig {
        private String customerEmail; //PII data
        private String defaultEmailEncoding;
        private String mailHost;
        private String senderEmail; // This will be figured realtime or kept encrypted in config
        private String sendersEmailPassword;
        private String mailPort;
        private String mailProtocol;
        private boolean mailSmtpAuth;
        private boolean smtpStartTLS;
    }
}

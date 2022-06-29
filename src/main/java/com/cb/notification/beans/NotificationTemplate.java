package com.cb.notification.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class NotificationTemplate {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String templateId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String templateName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String templateType;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String emailSubject;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String emailContent;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String smsContent;
}

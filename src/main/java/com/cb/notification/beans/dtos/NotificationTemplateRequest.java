package com.cb.notification.beans.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NotBlank
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationTemplateRequest {
    @NotBlank
    private String customerId;
    private List<NotificationParameter> notificationParameters;
    // TODO: these should be coming from preference service for the given customer Id
    private String notificationTemplateName;
    private String notificationMode;


}

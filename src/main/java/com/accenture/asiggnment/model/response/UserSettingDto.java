package com.accenture.asiggnment.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "biometric_login",
        "push_notification",
        "sms_notification",
        "show_onboarding",
        "widget_order"
})
public class UserSettingDto {
    @JsonProperty("biometric_login")
    private String biometricLogin;
    @JsonProperty("push_notification")
    private String pushNotification;
    @JsonProperty("sms_notification")
    private String smsNotification;
    @JsonProperty("show_onboarding")
    private String showOnboarding;
    @JsonProperty("widget_order")
    private String widgetOrder;
}

package com.accenture.asiggnment.util;

import com.accenture.asiggnment.model.UserSetting;
import com.accenture.asiggnment.model.response.UserSettingDto;

import java.util.LinkedList;
import java.util.List;

public class CommonUtil {
    public static final String formatSsn(String ssn){
        String result="";
        String zero="";
        if(ssn.length()<16){
            for(int i=0;i<16-ssn.length();i++){
                result=result.concat("0");
            }
        }else{
            return ssn;
        }
        return result.concat(ssn);
    }
    public static List<UserSettingDto> getUserSetting(List<UserSetting> userSettingsList){
        List<UserSettingDto> userSettingDtoList =new LinkedList<>();
        for(UserSetting userSetting: userSettingsList ){
            UserSettingDto userSettingDto =new UserSettingDto();
            if(userSetting.getKey().equalsIgnoreCase("biometric_login"))
                userSettingDto.setBiometricLogin(userSetting.getValue());
            if(userSetting.getKey().equalsIgnoreCase("push_notification"))
                userSettingDto.setPushNotification(userSetting.getValue());
            if(userSetting.getKey().equalsIgnoreCase("sms_notification"))
                userSettingDto.setSmsNotification(userSetting.getValue());
            if(userSetting.getKey().equalsIgnoreCase("show_onboarding"))
                userSettingDto.setShowOnboarding(userSetting.getValue());
            if(userSetting.getKey().equalsIgnoreCase("widget_order"))
                userSettingDto.setWidgetOrder(userSetting.getValue());
            userSettingDtoList.add(userSettingDto);

        }
        return userSettingDtoList;
    }



}

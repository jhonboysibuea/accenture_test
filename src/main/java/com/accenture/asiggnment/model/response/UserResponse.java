package com.accenture.asiggnment.model.response;

import com.accenture.asiggnment.model.Users;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Users user_data;
    private List<UserSettingDto> user_settings;
}

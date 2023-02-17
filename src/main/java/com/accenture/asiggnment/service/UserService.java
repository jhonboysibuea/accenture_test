package com.accenture.asiggnment.service;

import com.accenture.asiggnment.model.Users;
import com.accenture.asiggnment.model.request.UserDto;
import com.accenture.asiggnment.model.response.AllUserResponse;
import com.accenture.asiggnment.model.response.UserResponse;
import com.accenture.asiggnment.model.response.UserSettingDto;

import java.util.List;

public interface UserService {
    AllUserResponse getAllUsers(int max_records, int offset);
    void addUser(Users users);

    UserResponse saveUser(UserDto userDto);

    UserResponse getDetail(Long id);

    UserResponse updateUser(Long id, UserDto updateDto);

    UserResponse updateUserSetting(Long id, List<UserSettingDto> userSettingDto);

    void deleteUsers(Long id);
    UserResponse refreshUser(Long id);

}

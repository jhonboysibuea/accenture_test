package com.accenture.asiggnment.service;

import com.accenture.asiggnment.exception.DataNotFoundException;
import com.accenture.asiggnment.model.UserSetting;
import com.accenture.asiggnment.model.Users;
import com.accenture.asiggnment.model.request.UserDto;
import com.accenture.asiggnment.model.response.AllUserResponse;
import com.accenture.asiggnment.model.response.UserResponse;
import com.accenture.asiggnment.model.response.UserSettingDto;
import com.accenture.asiggnment.repository.UserRepository;
import com.accenture.asiggnment.repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

import static com.accenture.asiggnment.util.CommonUtil.formatSsn;
import static com.accenture.asiggnment.util.CommonUtil.getUserSetting;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSettingRepository userSettingRepository;

    @Override
    public AllUserResponse getAllUsers(int max_records, int offset) {
        AllUserResponse allUserResponse=new AllUserResponse();
        Pageable pageable= PageRequest.of(offset,max_records);
        Page<Users> usersPage=userRepository.findAllPagination(pageable);
        allUserResponse.setUserData(usersPage.getContent());

        allUserResponse.setOffSet(offset);
        allUserResponse.setMaxRecords(max_records);
        return allUserResponse;
    }

    @Override
    public void addUser(Users users) {
        userRepository.save(users);

    }

    @Override
    public UserResponse saveUser(UserDto userDto) {
        UserResponse userResponse=new UserResponse();

        List<UserSettingDto> userSettingDtoList =new LinkedList<>();
        Users users=new Users();
        String ssn= userDto.getSsn();
        String ssnFinal=formatSsn(ssn);
        users.setSsn(ssnFinal);
        users.setFirst_name(userDto.getFirst_name());
        users.setLast_name(userDto.getLast_name());
        users.setBirth_date(userDto.getBirth_date());
        users.setUpdated_by("SYSTEM");
        users.setCreated_by("SYSTEM");
        users.setCreated_time(new Date());
        users.setUpdated_time(new Date());
        Users usersExit=userRepository.save(users);

        UserSetting userSetting1=new UserSetting();
        userSetting1.setUser_id(users);
        userSetting1.setKey("biometric_login");
        userSetting1.setValue("false");


        UserSetting userSetting2=new UserSetting();
        userSetting2.setUser_id(users);
        userSetting2.setKey("push_notification");
        userSetting2.setValue("false");


        UserSetting userSetting3=new UserSetting();
        userSetting3.setUser_id(users);
        userSetting3.setKey("sms_notification");
        userSetting3.setValue("false");


        UserSetting userSetting4=new UserSetting();
        userSetting4.setUser_id(users);
        userSetting4.setKey("show_onboarding");
        userSetting4.setValue("false");


        UserSetting userSetting5=new UserSetting();
        userSetting5.setUser_id(users);
        userSetting5.setKey("widget_order");
        userSetting5.setValue("1,2,3,4,5");
        userResponse.setUser_data(users);

        userSettingRepository.save(userSetting1);
        userSettingRepository.save(userSetting2);
        userSettingRepository.save(userSetting3);
        userSettingRepository.save(userSetting4);
        userSettingRepository.save(userSetting5);

        List<UserSetting> userSettingsList=userSettingRepository.findAllByUserId(usersExit.getId());

        userSettingDtoList =getUserSetting(userSettingsList);

        userResponse.setUser_settings(userSettingDtoList);
        return userResponse;
    }

    @Override
    public UserResponse getDetail(Long id) {
        UserResponse userResponse=new UserResponse();
        Users usersExist= userRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cannot find resource with id "+id));


        List<UserSettingDto> userSettingDtoList =new LinkedList<>();
        List<UserSetting> userSettingsList=userSettingRepository.findAllByUserId(usersExist.getId());
        userSettingDtoList =getUserSetting(userSettingsList);

        userResponse.setUser_data(usersExist);
        userResponse.setUser_settings(userSettingDtoList);
        return userResponse;
    }

    @Override
    public UserResponse updateUser(Long id, UserDto updateDto) {
        UserResponse userResponse=new UserResponse();
        Users users= userRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cannot find resource with id"+id));
        String ssn=updateDto.getSsn();
        String ssnFinal=formatSsn(ssn);
        users.setSsn(ssnFinal);
        users.setFirst_name(updateDto.getFirst_name());
        users.setLast_name(updateDto.getLast_name());
        users.setBirth_date(updateDto.getBirth_date());
        users.setUpdated_by("SYSTEM");
        users.setCreated_by("SYSTEM");
        users.setCreated_time(new Date());
        users.setUpdated_time(new Date());
        userRepository.save(users);
        List<UserSettingDto> userSettingDtoList =new LinkedList<>();
        List<UserSetting> userSettingsList=userSettingRepository.findAllByUserId(users.getId());
        userSettingDtoList =getUserSetting(userSettingsList);
        userResponse.setUser_data(users);
        userResponse.setUser_settings(userSettingDtoList);
        return userResponse;
    }

    @Override
    public UserResponse updateUserSetting(Long id, List<UserSettingDto> userSettingDto) {
        UserResponse userResponse=new UserResponse();
        Users users= userRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cannot find resource with id"+id));
        List<UserSettingDto> userSettingDtoList =new LinkedList<>();
        List<UserSetting> userSettingsList=userSettingRepository.findAllByUserId(users.getId());
        String biometric=userSettingDto.get(0).getBiometricLogin();
        String push_notification=userSettingDto.get(1).getPushNotification();
        String sms_notification=userSettingDto.get(2).getSmsNotification();
        String show_onboarding=userSettingDto.get(3).getShowOnboarding();
        String widget_order=userSettingDto.get(4).getWidgetOrder();
        List<UserSetting> userSettings=new LinkedList<>();
        for(UserSetting userSetting: userSettingsList){

            if(userSetting.getKey().equalsIgnoreCase("biometric_login")){
                userSetting.setValue(biometric);
                userSetting.setKey("biometric_login");
                userSettings.add(userSetting);

            }
            if(userSetting.getKey().equalsIgnoreCase("push_notification")){
                userSetting.setKey("push_notification");
                userSetting.setValue(push_notification);
                userSettings.add(userSetting);
            }
            if(userSetting.getKey().equalsIgnoreCase("sms_notification")){
                userSetting.setKey("sms_notification");
                userSetting.setValue(sms_notification);
                userSettings.add(userSetting);
            }
            if(userSetting.getKey().equalsIgnoreCase("show_onboarding")){
                userSetting.setKey("show_onboarding");
                userSetting.setValue(show_onboarding);
                userSettings.add(userSetting);
            }
            if(userSetting.getKey().equalsIgnoreCase("widget_order")){
                userSetting.setKey("widget_order");
                userSetting.setValue(widget_order);
                userSettings.add(userSetting);
            }


        }
        userSettingRepository.saveAll(userSettings);
        userSettingDtoList =getUserSetting(userSettingsList);
        userResponse.setUser_data(users);
        userResponse.setUser_settings(userSettingDtoList);
        return userResponse;
    }

    @Override
    public void deleteUsers(Long id) {
        Users users= userRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cannot find resource with id"+id));

        users.setDeleted_time(new Date());
        users.set_active(false);
        userRepository.save(users);
    }

    @Override
    public UserResponse refreshUser(Long id) {
        UserResponse userResponse=new UserResponse();
        Users users= userRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cannot find resource with id"+id));

        users.set_active(true);
        users.setDeleted_time(null);
        userRepository.save(users);

        List<UserSettingDto> userSettingDtoList =new LinkedList<>();
        List<UserSetting> userSettingsList=userSettingRepository.findAllByUserId(users.getId());
        userSettingDtoList =getUserSetting(userSettingsList);
        userResponse.setUser_data(users);
        userResponse.setUser_settings(userSettingDtoList);
        return userResponse;
    }
}

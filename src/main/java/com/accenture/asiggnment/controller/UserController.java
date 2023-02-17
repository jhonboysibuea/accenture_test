package com.accenture.asiggnment.controller;

import com.accenture.asiggnment.exception.DataNotFoundException;
import com.accenture.asiggnment.model.request.UserDto;
import com.accenture.asiggnment.model.response.CustomErrorResponse;
import com.accenture.asiggnment.model.response.UserResponse;
import com.accenture.asiggnment.model.response.UserSettingDto;
import com.accenture.asiggnment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    ResponseEntity getAllUsers(@RequestParam(value = "max_records",defaultValue = "5") String max_records, @RequestParam(value = "offset",defaultValue = "0") String offset){
        try{
            int size= Integer.parseInt(max_records);
            int page= Integer.parseInt(offset);
            return new ResponseEntity(userService.getAllUsers(size,page),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(CustomErrorResponse.systemError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/users")
    ResponseEntity register(@RequestBody @Valid UserDto userDto){

           UserResponse userResponse=userService.saveUser(userDto);
           return new ResponseEntity(userResponse,HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    ResponseEntity getDetail(@PathVariable("id")Long id){

        UserResponse userResponse=userService.getDetail(id);
        return new ResponseEntity(userResponse,HttpStatus.OK);

    }

    @PutMapping("/users/{id}")
    ResponseEntity  updateUser(@PathVariable("id")Long id,@Valid @RequestBody UserDto updateDto){
        try{
            UserResponse userResponse=userService.updateUser(id,updateDto);
            return new ResponseEntity(userResponse,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(CustomErrorResponse.systemError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/users/{id}/settings")
    ResponseEntity  updateSettingUser(@PathVariable("id")Long id,@RequestBody List<UserSettingDto> userSettingDto){
        try{
            UserResponse userResponse=userService.updateUserSetting(id,userSettingDto);
            return new ResponseEntity(userResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(CustomErrorResponse.systemError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity  softDeleteUser(@PathVariable("id")Long id){
        try{
            userService.deleteUsers(id);
            return new ResponseEntity("",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(CustomErrorResponse.systemError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}/refresh")
    ResponseEntity  refresh(@PathVariable("id")Long id){
        try{
            UserResponse userResponse= userService.refreshUser(id);
            return new ResponseEntity(userResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(CustomErrorResponse.systemError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

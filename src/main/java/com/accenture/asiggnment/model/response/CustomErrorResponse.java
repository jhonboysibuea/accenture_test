package com.accenture.asiggnment.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomErrorResponse {
    private String status;
    private int code;
    private List<String> messages;

    //404	30000	Cannot find resource with id {the_id}
    //409	30001	Record with unique value {the_value} already exists in the system
    //422	30002	Invalid value for field {field_name}, rejected value: {the_value}
    //500	80000	System error, we're unable to process your request at the moment
    public static CustomErrorResponse buildNotValid(List<String> messages){
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(),30000,messages);
    }
    public static  CustomErrorResponse buildNotUnique(List<String> messages){
        return new CustomErrorResponse(HttpStatus.CONFLICT.getReasonPhrase(), 30001,messages);
    }
    public static CustomErrorResponse invalidValue(List<String> messages){
        return new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),30002,messages);
    }
    public static CustomErrorResponse systemError(){
        List<String> message=new LinkedList<>();
        message.add("System error, we're unable to process your request at the moment");
        return new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 30001,message);
    }
}

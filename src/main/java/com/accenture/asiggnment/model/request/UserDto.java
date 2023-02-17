package com.accenture.asiggnment.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {
     @Pattern(message = "Invalid value for field ssn, rejected value: {0}", regexp="^[0-9]*$")
     private String ssn;
     @Pattern(message = "Invalid value for field first_name, rejected value: {0}", regexp="^[a-z]*$")
     private String first_name;
     @Pattern(message = "Invalid value for field last_name, rejected value: {0}", regexp="^[a-z]*$")
     private String last_name;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private String birth_date;
}

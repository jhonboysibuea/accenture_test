package com.accenture.asiggnment.model.response;

import com.accenture.asiggnment.model.Users;
import lombok.Data;

import java.util.List;

@Data
public class AllUserResponse {
    private List<Users> userData;
    private int maxRecords;
    private int offSet;

}

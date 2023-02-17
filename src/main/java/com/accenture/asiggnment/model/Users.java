package com.accenture.asiggnment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Data // Create getters and setters
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ssn;

    private String first_name;
    private String middle_name;
    private String last_name;
    private String family_name;
    private String birth_date;
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_time;
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    private Date updated_time;
    private String created_by;
    private String updated_by;
    @JsonProperty("is_active")
    private boolean is_active=true;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deleted_time;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "user_setting_id")
    private List<UserSetting> userSetting;
}

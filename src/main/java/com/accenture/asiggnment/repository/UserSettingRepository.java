package com.accenture.asiggnment.repository;

import com.accenture.asiggnment.model.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserSettingRepository extends JpaRepository<UserSetting,Long> {

    @Query("select u from UserSetting u where u.user_id.id=:userId")
    List<UserSetting> findAllByUserId(Long userId);
}

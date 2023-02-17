package com.accenture.asiggnment.repository;

import com.accenture.asiggnment.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users,Long> {
    @Query("select u from Users u where u.is_active=true")
    Page<Users> findAllPagination(Pageable pageable);
}

package com.geekplus.springsecurity.repository;

import com.geekplus.springsecurity.entity.Role;
import com.geekplus.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

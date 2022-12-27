package com.geekplus.springsecurity.repository;

import com.geekplus.springsecurity.entity.Permission;
import com.geekplus.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}

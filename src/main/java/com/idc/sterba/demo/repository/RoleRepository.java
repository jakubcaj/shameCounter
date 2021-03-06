package com.idc.sterba.demo.repository;

import com.idc.sterba.demo.entity.secure.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

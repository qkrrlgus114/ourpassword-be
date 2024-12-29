package com.park.ourpassword.domain.encryption.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.park.ourpassword.domain.encryption.module.entity.EncryptModule;

public interface ModuleRepository extends JpaRepository<EncryptModule, Long> {
}

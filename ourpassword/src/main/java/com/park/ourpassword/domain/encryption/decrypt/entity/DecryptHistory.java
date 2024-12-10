package com.park.ourpassword.domain.encryption.decrypt.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.park.ourpassword.domain.encryption.module.entity.EncryptModule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class DecryptHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 45)
	private String ip;

	@CreatedDate
	private LocalDateTime accessAt;

	@Column(nullable = false, length = 500)
	private String secretKey;

	@Column(nullable = false, length = 100)
	private String encryptedPassword;

	@Column(nullable = false, length = 200)
	private String decryptedPassword;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encrypt_module_id")
	private EncryptModule encryptModule;
}

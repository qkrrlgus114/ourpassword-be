package com.park.ourpassword.domain.member.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class VisitorHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 45)
	private String ip;

	@CreatedDate
	private LocalDateTime accessAt;

	@Builder
	public VisitorHistory(String ip, LocalDateTime accessAt) {
		this.ip = ip;
		this.accessAt = accessAt;
	}
}

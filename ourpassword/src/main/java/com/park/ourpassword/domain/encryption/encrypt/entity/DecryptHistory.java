package com.park.ourpassword.domain.encryption.encrypt.entity;

import com.park.ourpassword.domain.encryption.module.entity.EncryptModule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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

    @Builder
    public DecryptHistory(String ip, LocalDateTime accessAt, String secretKey, String encryptedPassword, String decryptedPassword, EncryptModule encryptModule) {
        this.ip = ip;
        this.accessAt = accessAt;
        this.secretKey = secretKey;
        this.encryptedPassword = encryptedPassword;
        this.decryptedPassword = decryptedPassword;
        this.encryptModule = encryptModule;
    }
}

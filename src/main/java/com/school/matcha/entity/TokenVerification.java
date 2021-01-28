package com.school.matcha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens_verification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenVerification {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private User user;
}

package inu.spp.cryptocoffee.auth.email.entity;

import inu.spp.cryptocoffee.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "email_auth", indexes = @Index(name = "idx_email", columnList = "email"))
@Entity
public class EmailAuthEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    private String authNum;

    @Column(name = "is_auth")
    private boolean isAuth = false;

    public void updateIsAuthTrue() {
        this.isAuth = true;
    }

    public boolean isAuth() {
        return this.isAuth;
    }

    public void updateAuthNum(String authNum) {
        this.authNum = authNum;
    }
}

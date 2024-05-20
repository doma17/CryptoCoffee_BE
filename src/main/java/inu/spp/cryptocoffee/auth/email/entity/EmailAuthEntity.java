package inu.spp.cryptocoffee.auth.email.entity;

import inu.spp.cryptocoffee.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public EmailAuthEntity(String email) {
        this.email = email;
    }

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

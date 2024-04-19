package inu.spp.cryptocoffee.user.entity;

import inu.spp.cryptocoffee.email.entity.CompanyEntity;
import inu.spp.cryptocoffee.global.BaseTimeEntity;
import inu.spp.cryptocoffee.user.dto.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "user")
@Entity
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private boolean isLocked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;
}

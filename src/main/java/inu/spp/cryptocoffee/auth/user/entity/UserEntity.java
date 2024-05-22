package inu.spp.cryptocoffee.auth.user.entity;

import inu.spp.cryptocoffee.auth.user.dto.UserRoleEnum;
import inu.spp.cryptocoffee.domain.entity.CompanyEntity;
import inu.spp.cryptocoffee.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
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
    private boolean isLocked = true;

    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;

    public void changeRole(UserRoleEnum role) {
        this.role = role;
    }

    public void unlockUser() {
        this.isLocked = false;
    }
}

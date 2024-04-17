package inu.spp.cryptocoffee.user.entity;

import inu.spp.cryptocoffee.email.entity.CompanyEntity;
import inu.spp.cryptocoffee.global.BaseTimeEntity;
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

    private String username;

    private String password;

    private String name;

    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;
}
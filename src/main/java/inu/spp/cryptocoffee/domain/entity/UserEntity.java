package inu.spp.cryptocoffee.domain.entity;

import inu.spp.cryptocoffee.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "employee_number")
    private String employeeNumber;

    private String email;

    @Column(name = "account_address")
    private String accountAddress;

    private String name;

    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;
}

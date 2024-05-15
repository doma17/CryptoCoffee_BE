package inu.spp.cryptocoffee.email.entity;

import inu.spp.cryptocoffee.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "company")
public class CompanyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long companyId;

    private String name;

    @Column(name = "number")
    private String companyNumber;

    @Embedded
    private Address address;
}

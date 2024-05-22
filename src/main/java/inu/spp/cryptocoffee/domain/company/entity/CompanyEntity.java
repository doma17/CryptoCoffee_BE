package inu.spp.cryptocoffee.domain.company.entity;

import inu.spp.cryptocoffee.domain.company.dto.Address;
import inu.spp.cryptocoffee.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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

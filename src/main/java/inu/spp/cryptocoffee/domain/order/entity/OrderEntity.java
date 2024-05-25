package inu.spp.cryptocoffee.domain.order.entity;

import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.item.entity.ItemEntity;
import inu.spp.cryptocoffee.domain.member.entity.MemberEntity;
import inu.spp.cryptocoffee.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "orders")
public class OrderEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @ManyToOne
    private ItemEntity item;

    @ManyToOne
    private MemberEntity member;

    @ManyToOne
    private CompanyEntity company;

    public void updateOrder(ItemEntity item, MemberEntity member) {
        this.item = item;
        this.member = member;
    }
}

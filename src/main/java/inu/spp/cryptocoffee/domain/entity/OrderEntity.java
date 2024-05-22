package inu.spp.cryptocoffee.domain.entity;

import inu.spp.cryptocoffee.global.base.BaseTimeEntity;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
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
    private UserEntity user;

    public void updateOrder(ItemEntity item, UserEntity user) {
        this.item = item;
        this.user = user;
    }
}

package inu.spp.cryptocoffee.domain.entity;

import inu.spp.cryptocoffee.global.BaseTimeEntity;
import inu.spp.cryptocoffee.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public OrderEntity(ItemEntity item, UserEntity user) {
        this.item = item;
        this.user = user;
    }

    public void updateOrder(ItemEntity item, UserEntity user) {
        this.item = item;
        this.user = user;
    }
}

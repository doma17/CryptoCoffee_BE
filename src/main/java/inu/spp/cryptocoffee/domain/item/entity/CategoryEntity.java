package inu.spp.cryptocoffee.domain.item.entity;


import inu.spp.cryptocoffee.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "category")
public class CategoryEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long categoryId;

    private String name;
}

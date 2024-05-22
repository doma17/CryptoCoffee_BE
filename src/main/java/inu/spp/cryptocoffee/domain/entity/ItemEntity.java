package inu.spp.cryptocoffee.domain.entity;


import inu.spp.cryptocoffee.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@DynamicUpdate
@Table(name = "item")
public class ItemEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long itemId;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    public void updateItem(String name, String description, CategoryEntity category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }
}

package inu.spp.cryptocoffee.domain.dto;

import inu.spp.cryptocoffee.domain.entity.ItemEntity;
import inu.spp.cryptocoffee.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long itemId;
    private Long userId;
}
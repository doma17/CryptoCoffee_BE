package inu.spp.cryptocoffee.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long itemId;
    private Long userId;
}
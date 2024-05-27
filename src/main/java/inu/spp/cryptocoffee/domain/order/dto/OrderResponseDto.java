package inu.spp.cryptocoffee.domain.order.dto;

import inu.spp.cryptocoffee.domain.order.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

    private Long itemId;

    private Long memberId;

    public static OrderResponseDto from(OrderEntity order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.itemId = order.getItem().getItemId();
        orderResponseDto.memberId = order.getMember().getMemberId();
        return orderResponseDto;
    }
}

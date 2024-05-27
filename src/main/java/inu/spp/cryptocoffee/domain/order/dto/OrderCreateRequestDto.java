package inu.spp.cryptocoffee.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequestDto {

    private Long itemId;

    private Long memberId;

    private Long companyId;

}

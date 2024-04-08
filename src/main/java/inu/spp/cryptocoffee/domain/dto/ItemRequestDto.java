package inu.spp.cryptocoffee.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    private String name;
    private String description;
    private Long categoryId;
}

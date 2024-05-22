package inu.spp.cryptocoffee.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequestDto {

    private String name;

    private String job;

    private String email;

}

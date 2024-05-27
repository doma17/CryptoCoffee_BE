package inu.spp.cryptocoffee.domain.member.dto;

import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.member.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequestDto {

    private String name;

    private String position;

    private String email;

    private String companyName;

    public static MemberEntity toEntity(MemberJoinRequestDto memberJoinRequestDto, CompanyEntity company) {
        return MemberEntity.builder()
                .name(memberJoinRequestDto.getName())
                .position(memberJoinRequestDto.getPosition())
                .email(memberJoinRequestDto.getEmail())
                .status(MemberStatus.INACTIVE)
                .company(company)
                .build();
    }

}

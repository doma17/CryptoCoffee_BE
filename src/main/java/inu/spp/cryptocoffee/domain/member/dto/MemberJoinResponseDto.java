package inu.spp.cryptocoffee.domain.member.dto;

import inu.spp.cryptocoffee.domain.member.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberJoinResponseDto {

    private String name;

    private String job;

    private String email;

    private LocalDateTime updatedAt;

    public static MemberJoinResponseDto from(MemberEntity memberEntity) {
        return MemberJoinResponseDto.builder()
                .name(memberEntity.getName())
                .job(memberEntity.getJob())
                .email(memberEntity.getEmail())
                .updatedAt(memberEntity.getUpdatedAt())
                .build();
    }
}

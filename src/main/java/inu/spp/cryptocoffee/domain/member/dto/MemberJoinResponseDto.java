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

    private String position;

    private String email;

    private LocalDateTime updatedAt;

    public static MemberJoinResponseDto from(MemberEntity memberEntity) {
        return MemberJoinResponseDto.builder()
                .name(memberEntity.getName())
                .position(memberEntity.getPosition())
                .email(memberEntity.getEmail())
                .updatedAt(memberEntity.getUpdatedAt())
                .build();
    }
}

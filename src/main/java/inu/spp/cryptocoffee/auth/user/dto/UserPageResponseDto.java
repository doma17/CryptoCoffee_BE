package inu.spp.cryptocoffee.auth.user.dto;

import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class UserPageResponseDto {

    private String username;

    private String name;

    private UserRoleEnum role;

    private String companyName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static UserPageResponseDto from(UserEntity userEntity) {
        UserPageResponseDto userPageResponseDto = new UserPageResponseDto();
        userPageResponseDto.setUsername(userEntity.getUsername());
        userPageResponseDto.setName(userEntity.getName());
        userPageResponseDto.setRole(userEntity.getRole());
        userPageResponseDto.setCompanyName(userEntity.getCompany().getName());
        userPageResponseDto.setCreatedAt(userEntity.getCreatedAt());
        userPageResponseDto.setUpdatedAt(userEntity.getUpdatedAt());
        return userPageResponseDto;
    }
}

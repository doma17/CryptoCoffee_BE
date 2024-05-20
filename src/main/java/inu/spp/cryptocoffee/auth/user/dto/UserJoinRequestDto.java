package inu.spp.cryptocoffee.auth.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserJoinRequestDto {

    @Schema(description = "유저의 아이디", example = "test")
    @NotNull(message = "username is null")
    @NotEmpty(message = "username is empty")
    private String username;

    @Schema(description = "유저의 비밀번호", example = "test1234")
    @NotNull(message = "password is null")
    @NotEmpty(message = "password is empty")
    private String password;

    @Schema(description = "유저의 이름", example = "홍길동")
    @NotNull(message = "name is null")
    @NotEmpty(message = "name is empty")
    private String name;

    @Schema(description = "유저의 회사", example = "CryptoCoffee")
    @NotNull(message = "company is null")
    @NotEmpty(message = "company is empty")
    private String company;

    // Lombok 에러로 인해 getter, setter 직접 추가 (내부 prameter가 null이 되는 오류 발생)
    public @NotNull(message = "username is null") @NotEmpty(message = "username is empty") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "username is null") @NotEmpty(message = "username is empty") String username) {
        this.username = username;
    }

    public @NotNull(message = "password is null") @NotEmpty(message = "password is empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "password is null") @NotEmpty(message = "password is empty") String password) {
        this.password = password;
    }

    public @NotNull(message = "name is null") @NotEmpty(message = "name is empty") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "name is null") @NotEmpty(message = "name is empty") String name) {
        this.name = name;
    }

    public @NotNull(message = "company is null") @NotEmpty(message = "company is empty") String getCompany() {
        return company;
    }

    public void setCompany(@NotNull(message = "company is null") @NotEmpty(message = "company is empty") String company) {
        this.company = company;
    }
}

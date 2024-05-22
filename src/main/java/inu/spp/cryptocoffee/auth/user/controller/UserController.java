package inu.spp.cryptocoffee.auth.user.controller;

import inu.spp.cryptocoffee.auth.user.dto.UserAuthorityRequestDto;
import inu.spp.cryptocoffee.auth.user.dto.UserPageResponseDto;
import inu.spp.cryptocoffee.auth.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "User API")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 목록 조회")
    @GetMapping("/api/users")
    public List<UserPageResponseDto> getUsers(
            @RequestParam(required = false, defaultValue = "0", value = "page") int page,
            @RequestParam(required = false, defaultValue = "createdAt", value = "criteria") String criteria) {
        return userService.getUsers(page, criteria);
    }

    @Operation(summary = "유저 권한 변경")
    @PutMapping("/api/users/authority")
    public ResponseEntity<?> changeAuthority(@RequestBody UserAuthorityRequestDto userAuthorityRequestDto) {
        userService.changeAuthority(userAuthorityRequestDto);
        return ResponseEntity.ok().build();
    }
}

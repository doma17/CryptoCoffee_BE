package inu.spp.cryptocoffee.user.controller;

import inu.spp.cryptocoffee.user.dto.JoinDto;
import inu.spp.cryptocoffee.user.service.JoinService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "User Join API")
@RestController
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/user/join")
    public String joinProcess(@Valid @RequestBody JoinDto joinDTO) {

        log.info("[joinProcess] username: {}", joinDTO.getUsername());
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
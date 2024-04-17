package inu.spp.cryptocoffee.user.controller;

import inu.spp.cryptocoffee.user.dto.JoinDto;
import inu.spp.cryptocoffee.user.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDto joinDTO) {

        log.info("[joinProcess] username: {}", joinDTO.getUsername());
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
package inu.spp.cryptocoffee.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {
    private String username;
    private String password;
    private String name;
    private String company;
}

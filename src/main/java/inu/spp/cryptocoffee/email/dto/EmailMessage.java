package inu.spp.cryptocoffee.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailMessage {

    @Email
    @NotNull
    private String to;

    @NotNull
    private String subject;

    @NotNull
    private String message;

}

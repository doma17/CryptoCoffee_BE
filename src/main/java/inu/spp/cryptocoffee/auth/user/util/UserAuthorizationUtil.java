package inu.spp.cryptocoffee.auth.user.util;

import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthorizationUtil {

    private UserAuthorizationUtil() {
        throw new AssertionError();
    }

    public static UserEntity getLoginUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUserEntity();
    }
}

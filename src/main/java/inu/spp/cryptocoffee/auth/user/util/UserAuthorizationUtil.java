package inu.spp.cryptocoffee.auth.user.util;

import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthorizationUtil {

    private UserAuthorizationUtil() {
        throw new AssertionError();
    }

    public static Long getLoginUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUserId();
    }
}

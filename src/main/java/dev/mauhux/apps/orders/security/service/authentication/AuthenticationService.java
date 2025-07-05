package dev.mauhux.apps.orders.security.service.authentication;

import dev.mauhux.apps.orders.security.dto.LoginRequestDto;
import dev.mauhux.apps.orders.security.dto.LoginResponseDto;

public interface AuthenticationService {

    LoginResponseDto login(LoginRequestDto loginRequestDTO);
}

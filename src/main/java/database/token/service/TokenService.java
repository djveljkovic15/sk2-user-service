package database.token.service;

import database.token.domain.TokenResponse;
import database.user.domain.User;
import io.jsonwebtoken.Claims;

public interface TokenService {

    TokenResponse generate(User user);

    Claims parseToken(String jwt);
}

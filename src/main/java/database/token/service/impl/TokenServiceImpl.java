package database.token.service.impl;


import database.token.domain.TokenResponse;
import database.token.service.TokenService;

import database.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    @Override
    public TokenResponse generate(User user) {
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
//        claims.put("username", user.getUsername());
//        claims.put("email", user.getEmail());
//        claims.put("role", user.getRole().getName());

        return new TokenResponse(Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact());
    }

    @Override
    public Claims parseToken(String jwt) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        return claims;
    }

}

package com.example.site.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String SECRET_KEY= "makeformyexam";
    private final long TOKEN_VALID_TIME = 60 * 60 * 100L;
    private final UserDetailsService userDetailsService;
    private final String HEADER_STRING = "X-AUTH-TOKEN";

    @PostConstruct
    protected void init() {

        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());

    }

    public String createToken(String userId){

        Claims claims = Jwts.claims().setSubject(userId);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();

    }

    public Authentication getAuthentication(String token){

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());

    }

    public String getUserId(String token){

        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();

    }

    public String resolveToken(HttpServletRequest httpServletRequest){

        return httpServletRequest.getHeader(HEADER_STRING);

    }

    public boolean validateToken(String jwtToken){

        try{

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());

        } catch (Exception e){

            return false;

        }

    }
}

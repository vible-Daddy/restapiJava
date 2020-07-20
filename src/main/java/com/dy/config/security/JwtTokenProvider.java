package com.dy.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * JWT 토큰생성 및 검증
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    private UserDetailsService userDetailsService;

    private long tokenVaildMilisecond = 1000L * 60 * 60; // 1시간 토큰 유요시간

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * 토큰생성
     * @param userPk
     * @param roles
     * @return
     */
    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles",roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 발행일자
                .setExpiration(new Date(now.getTime() + tokenVaildMilisecond)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256,secretKey) // 알고리즘 , 암호화 키
                .compact();
    }

    // Jwt 토큰으로 인증 정보를 조회
    public Authentication getAuthentication(String token){
        System.out.println("/-----------------/");
        System.out.println(this.getUserPk(token));
        System.out.println("/-----------------/");
//        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        System.out.println("/-----------------/");
        System.out.println(this.getUserPk(token));
        System.out.println(userDetails);
        System.out.println("/-----------------/");
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    // Request의 Header에서 token 파싱 : "X-AUTH-TOKEN: jwt토큰"
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("X-AUTH-TOKEN");
    }
    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}

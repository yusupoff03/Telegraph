package com.example.telegraph.service;


import com.example.telegraph.entity.user.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.apache.catalina.realm.UserDatabaseRealm.getRoles;

public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.access.expiry}")
    private long accessTokenExpiry;

    public String generateAccessToken(UserEntity user){
    return Jwts.builder().
            signWith(SignatureAlgorithm.HS256,secretKey).
            setSubject(user.getUsername()).
            setIssuedAt(new Date()).
            setExpiration(new Date(new Date().getTime()+accessTokenExpiry)).
            addClaims(Map.of("roles",getRoles(user.getAuthorities()))).
            compact();
    }
    private List<String> getRoles(Collection<? extends GrantedAuthority> roles){
    return roles.stream().
            map(GrantedAuthority::getAuthority).
            toList();
    }
}

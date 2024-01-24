package project.ip.ecommerce.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import project.ip.ecommerce.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;
import java.util.function.Function;


@Service
public class JWTServiceImpl implements JWTService {
      public static final long RESET_TOKEN_EXPIRATION = 3600000;

     public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
        .setIssuedAt((new Date(System.currentTimeMillis())))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *24))
        .signWith(getSiginKey(), SignatureAlgorithm.HS256)
        .compact();
     }

     public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails){
      return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
      .setIssuedAt((new Date(System.currentTimeMillis())))
      .setExpiration(new Date(System.currentTimeMillis() + 604800000))
      .signWith(getSiginKey(), SignatureAlgorithm.HS256)
      .compact();
   }

     private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers){
      final Claims claims = extractAllClaims(token);
      return claimsResolvers.apply(claims);
     }
     private Key getSiginKey(){
        byte[] key = Decoders.BASE64.decode("gEOsPV6ib9Hz6JSoQbceMzOxrMyd3H72G0UxGyhT6Ck=");
        return Keys.hmacShaKeyFor(key);
     }

     public String extractUserName(String token){
      return extractClaim(token, Claims::getSubject);
     }

     private Claims extractAllClaims(String token){
      return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
     }

     public boolean isTokenValid(String token, UserDetails userDetails){
      final String userName = extractUserName(token);
      return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
     }

     private boolean isTokenExpired(String token){
      return extractClaim(token, Claims::getExpiration).before(new Date());
     }

     // Inside JWTServiceImpl.java
      // public String generateResetToken(UserDetails userDetails) {
      //    return Jwts.builder()
      //             .setSubject(userDetails.getUsername())
      //             .setIssuedAt(new Date())
      //             .setExpiration(new Date(System.currentTimeMillis() + RESET_TOKEN_EXPIRATION))
      //             .signWith(getSiginKey(), SignatureAlgorithm.HS256)
      //             .compact();
      // }

      // Inside JWTServiceImpl.java
      public String generateResetToken(UserDetails userDetails) {
         return Jwts.builder()
                 .setSubject(userDetails.getUsername())
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(System.currentTimeMillis() + RESET_TOKEN_EXPIRATION))
                 .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                 .compact();
     }

}

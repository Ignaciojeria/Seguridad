package com.example.zuul.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.zuul.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtTokenUtil {

	//Claims del token
	 static final String CLAIM_KEY_USERNAME = "sub";
	 static final String CLAIM_KEY_AUDIENCE = "audience";
	 static final String CLAIM_KEY_CREATED = "created";
	 
	 //Clave secreta asimetrica para el token!.
	 private String secret="ClaveSecreta";
	 
	 //Expiración del token! (expira en 15 minutos!)
	 private Long expiration=900000L;
	 
	 //Obtener los claims de un token generado
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
	
    //Obtener el usuario del claim de un token generado
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }
    
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
  /*  
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }*/

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
    
    //recibe un usuario para generar los claims y crea el token
    public String setUserForClaimsAndgenerateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, user.getUserName());
        //el token se crea con la fecha actual
        claims.put(CLAIM_KEY_CREATED, new Date(System.currentTimeMillis()));
        return generateToken(claims);
    }
   //recibe los claims para generar el token
   private String generateToken(Map<String, Object> claims) {
	   long tiempo=System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)//claims
                .setIssuedAt(new Date(tiempo)) //creación del token
                .setExpiration(new Date(tiempo+expiration)) //expiración del token
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
   
   public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
	   
       final Date created = getCreatedDateFromToken(token);
       
       return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
               && (!isTokenExpired(token));
   }
   
   public String refreshToken(String token) {
       String refreshedToken;
       try {
           final Claims claims = getClaimsFromToken(token);
           claims.put(CLAIM_KEY_CREATED, new Date());
           refreshedToken = generateToken(claims);
       } catch (Exception e) {
           refreshedToken = null;
       }
       return refreshedToken;
   }
   
   public Boolean validateToken(String token, User userDetails) {
       User user = userDetails;
       final String username = getUsernameFromToken(token);
       final Date created = getCreatedDateFromToken(token);
       //final Date expiration = getExpirationDateFromToken(token);
       return (
               username.equals(user.getUserName())
                       && !isTokenExpired(token)
                       && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
   }

    
    

}

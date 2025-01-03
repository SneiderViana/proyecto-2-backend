package proyecto_2_backend.proyecto_2_backend.security.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {

    private static final String JWT_SECRET_KEY= "TExBVkVfTVVZX1NFQ1JFVEzE3Zmxu7BSGSJx72BSBXM";
    private static final long JWT_TIME_VALIDITY =1000 * 60 * 15;

    public String generateTokend(UserDetails userDetails){
        var claims = new HashMap<String, Object>();
        return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME_VALIDITY))
                    .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                    .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return extractClaim(token, Claims::getSubject).equals(userDetails.getUsername())
                    && !extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T>claimsResolver){
        Claims claims= Jwts.parser().setSigningKey(JWT_SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

}

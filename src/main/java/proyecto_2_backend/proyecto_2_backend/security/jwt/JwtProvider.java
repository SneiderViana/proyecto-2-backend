package proyecto_2_backend.proyecto_2_backend.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import proyecto_2_backend.proyecto_2_backend.security.Service.UsuarioPrincipal;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

     private static final org.slf4j.Logger logger= LoggerFactory
                    .getLogger(JwtEntryPoint.class);

    public String generateToken(Authentication authentication ){
        UsuarioPrincipal usuarioPrincipal=(UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().signWith(getKey(secret))
        .setSubject(usuarioPrincipal.getUsername())
        .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+expiration * 1000))
        .claim("roles", getRoles(usuarioPrincipal)).compact();  
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey(secret))
        .build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
            return true;    
        } catch (ExpiredJwtException e) {
            logger.error("expired token");
        }catch(UnsupportedJwtException e){
            logger.error("Unsupported token");
        }catch(MalformedJwtException e){
            logger.error("Malformed token");
        }catch(IllegalArgumentException e){
            logger.error("bad signature");
        }catch(Exception e){
            logger.error("fail token");
        }
        return false;
    }

    private List<String> getRoles(UsuarioPrincipal principal){
        return principal.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

    }

    private Key getKey(String secret){
        byte [] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}

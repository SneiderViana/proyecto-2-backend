package proyecto_2_backend.proyecto_2_backend.security.jwt;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto_2_backend.proyecto_2_backend.security.Service.UsuarioDetailServiceImpl;


public class JwtFilter extends OncePerRequestFilter{
    
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(JwtFilter.class);
    
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioDetailServiceImpl usuarioDetailServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)throws ServletException, IOException {
        String token = getToken(req);
        try {
            if(token != null && jwtProvider.validateToken(token)){
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails=usuarioDetailServiceImpl.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }        
        } catch (UsernameNotFoundException e) {
            logger.error("filter block request");
        }
        chain.doFilter(req, res);
         
    }
    
    private String getToken(HttpServletRequest req) {
        String header=req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    
    }

                
}

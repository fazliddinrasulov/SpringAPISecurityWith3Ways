package uz.pdp.lesson.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.lesson.entity.User;
import uz.pdp.lesson.service.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = "";
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }
        if (!jwtUtils.isValid(token) ) {
            filterChain.doFilter(request, response);
            return;
        }
        String email = jwtUtils.getEmail(token);
        User user = userService.findByEmail(email).get();

        var obj = new UsernamePasswordAuthenticationToken(user.getEmail(), null, user.getRoles());
        SecurityContextHolder.getContext().setAuthentication(obj);
        filterChain.doFilter(request, response);
    }
}

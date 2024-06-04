package uz.pdp.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lesson.dto.LoginReq;
import uz.pdp.lesson.entity.User;
import uz.pdp.lesson.security.JwtUtils;
import uz.pdp.lesson.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    @PostMapping("login")
    public String login(@RequestBody LoginReq loginReq) {
        User user = userService.findByEmail(loginReq.email()).get();
        if(!passwordEncoder.matches(loginReq.password(),user.getPassword())) {
            return "xatolik";
        }
        return jwtUtils.generateToke(loginReq.email());
    }
}

package uz.pdp.lesson.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.lesson.entity.Role;
import uz.pdp.lesson.entity.User;
import uz.pdp.lesson.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class runnable implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) {
        userService.save(User.builder()
                .email("eshmat@gmail.com")
                .password(passwordEncoder.encode("qwe"))
                .roles(List.of(Role.builder().name("ROLE_ADMIN").build()))
                .build());
        userService.save(User.builder()
                .email("toshmat@gmail.com")
                .roles(List.of(Role.builder().name("ROLE_USER").build()))
                .password(passwordEncoder.encode("qwe"))
                .build());
    }
}

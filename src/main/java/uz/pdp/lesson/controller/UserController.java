package uz.pdp.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lesson.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")

public class UserController {
    private final UserService userService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public HttpEntity<?> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}

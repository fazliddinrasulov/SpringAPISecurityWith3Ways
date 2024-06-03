package uz.pdp.lesson.service;

import org.springframework.stereotype.Service;
import uz.pdp.lesson.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getAllUsers();
    Optional<User> findByEmail(String email);

    User save(User qwe);
}

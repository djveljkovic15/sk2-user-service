package database.user.service;


import database.role.domain.Role;
import database.token.domain.TokenRequest;
import database.token.domain.TokenResponse;
import database.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user, Role role);

    User update(Long id, User user, Role role);

    void deleteById(Long id);

    User findById(Long id);

    List<User> findAll();

    User findByUserAndPass(String username, String password);

    Optional<User> bannedList(String username);

    User banUser(Long userId, User bannedBy);

    TokenResponse login(TokenRequest request);
}

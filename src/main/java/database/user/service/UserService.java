package database.user.service;


import database.user.domain.User;

import java.util.List;

public interface UserService {
    User saveOrUpdate(User user);

    void deleteById(Long id);

    User findById(Long id);

    List<User> findAll();

//    User banUser(Long userId, User admin);
}

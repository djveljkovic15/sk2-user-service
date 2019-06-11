package database.user.service.impl;

import database.user.domain.User;
import database.user.repositorium.UserRepository;
import database.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User saveOrUpdate(User user) {
        if(user.getName()==null || user.getName().isEmpty())
            user.setName("Not provided");

        if(!(user.getUser_role()==null) ||)

        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

//    @Override
//    public User banUser(Long userId, User admin) {
//        return 1;
//    }


}

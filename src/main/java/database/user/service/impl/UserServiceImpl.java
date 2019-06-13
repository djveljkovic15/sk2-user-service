package database.user.service.impl;

import database.role.domain.Role;
import database.token.domain.TokenRequest;
import database.token.domain.TokenResponse;
import database.token.service.TokenService;
import database.user.domain.User;
import database.user.repositorium.UserRepository;
import database.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TokenService tokenService;


    public UserServiceImpl(UserRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }


    @Override
    public User save(User user, Role role) {
//        if(user.getName()==null || user.getName().isEmpty())
//            user.setName("Not provided");
//
//        if(!(user.getUser_role()==null) ||)

        return repository.save(user);
    }
    @Override
    public User update(Long id, User user, Role role) {
        if(findById(id)==null)
            return null;
        user.setId(id);
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

    @Override
    public TokenResponse login(TokenRequest request) {
            repository.findAll().stream().filter(user -> userFilter(user, request.getUsername(), request.getPassword()))
                    .findFirst().map(tokenService::generate);


            return null;
    }
private boolean userFilter(User user, String username, String password){
        return user.getUsername().equals(username)&&user.getPassword().equals(password)&&!user.isBanned();
}
//    @Override
//    public User banUser(Long userId, User admin) {
//        return 1;
//    }


}

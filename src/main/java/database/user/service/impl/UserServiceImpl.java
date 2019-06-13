package database.user.service.impl;

import database.role.domain.Role;
import database.token.domain.TokenRequest;
import database.token.domain.TokenResponse;
import database.token.service.TokenService;
import database.user.domain.Ban;
import database.user.domain.User;
import database.user.repositorium.UserRepository;
import database.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository, TokenService tokenService, UserRepository userRepository) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }


    @Override
    public User save(User user, Role role) {
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
    public User findByUserAndPass(String username, String password) {
        return repository.findAll().stream().filter(user ->
                user.getUsername().equals(username)&&user.getPassword().equals(password)&&!user.isBanned())
                .findFirst().orElse(null);
    }

    @Override
    public Optional<User> bannedList(String username) {
        return repository.findAll().stream()
                .filter(user -> user.getUsername().equals(username) && user.isBanned())
                .findFirst();
    }

    @Override
    public User banUser(Long userId, User bannedBy) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return null;
        Ban banHistory = new Ban();
        banHistory.setBannedBy(bannedBy.getUsername());
        banHistory.setDate(new Date());

        user.setBanned(true);
        user.getBanHistory().add(banHistory);

        return repository.save(user);
    }

    @Override
    public TokenResponse login(TokenRequest request) {
        User user = findByUserAndPass(request.getUsername(), request.getPassword());
        if(user==null)
                return null;

        return tokenService.generate(user);
    }

}

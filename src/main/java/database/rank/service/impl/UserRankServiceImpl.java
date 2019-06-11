package database.rank.service.impl;

import database.rank.domain.UserRank;
import database.rank.repositorium.UserRankRepository;
import database.rank.service.UserRankService;
import database.role.domain.Role;
import database.role.repositorium.RoleRepository;
import database.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRankServiceImpl implements UserRankService {

    private final UserRankRepository repository;

    public UserRankServiceImpl(UserRankRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserRank saveOrUpdate(UserRank userRank) {
        return repository.save(userRank);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserRank findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public UserRank findByName(String name){
        return repository.findAll().stream().filter(userRank -> userRank.getName().name()
                .equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    @Override
    public List<UserRank> findAll() {
        return repository.findAll();
    }
}

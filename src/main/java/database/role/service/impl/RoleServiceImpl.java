package database.role.service.impl;

import database.role.domain.Role;
import database.role.repositorium.RoleRepository;
import database.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role update(Long id, Role role) {
        if(findById(id)==null)
            return null;
        return repository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Role findByName(String name){
        return repository.findAll().stream().filter(role -> role.getName().name()
                .equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }
}

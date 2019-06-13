package database.role.service;

import database.role.domain.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role update(Long id, Role role);

    void deleteById(Long id);

    Role findById(Long id);

    Role findByName(String name);

    List<Role> findAll();
}

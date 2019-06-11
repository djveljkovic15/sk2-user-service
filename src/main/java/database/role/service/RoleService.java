package database.role.service;

import database.role.domain.Role;

import java.util.List;

public interface RoleService {
    Role saveOrUpdate(Role role);

    void deleteById(Long id);

    Role findById(Long id);

    Role findByName(String name);

    List<Role> findAll();
}

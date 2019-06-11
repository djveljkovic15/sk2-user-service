package database.rank.service;

import database.rank.domain.UserRank;
import database.role.domain.Role;

import java.util.List;

public interface UserRankService {
    UserRank saveOrUpdate(UserRank role);

    void deleteById(Long id);

    UserRank findById(Long id);

    UserRank findByName(String name);

    List<UserRank> findAll();
}

package database.rank.service;

import database.rank.domain.UserRank;

import java.util.List;

public interface UserRankService {
    UserRank save(UserRank role);

    UserRank update(Long id, UserRank role);

    void deleteById(Long id);

    UserRank findById(Long id);

    UserRank findByName(String name);

    List<UserRank> findAll();
}

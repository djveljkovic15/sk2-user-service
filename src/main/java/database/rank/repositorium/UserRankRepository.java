package database.rank.repositorium;

import database.rank.domain.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRankRepository extends JpaRepository<UserRank, Long> {
}

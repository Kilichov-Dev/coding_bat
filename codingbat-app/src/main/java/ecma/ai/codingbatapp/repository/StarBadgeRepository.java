package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.StarBadge;
import ecma.ai.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarBadgeRepository extends JpaRepository<StarBadge, Integer> {
//    boolean existsBySa
}

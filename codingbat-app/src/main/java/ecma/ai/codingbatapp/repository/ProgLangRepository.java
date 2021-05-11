package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.ProgLang;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface ProgLangRepository extends JpaRepository<ProgLang, Integer> {
    boolean existsByName(String name);
}

package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);
}

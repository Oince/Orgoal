package panoplie.orgoal.repository;

import panoplie.orgoal.domain.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
    Todo findById(Long id);
    void save(Todo todo);
    void delete(Long id);
    void updateFinished(Long id);
}

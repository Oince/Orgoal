package panoplie.orgoal.repository;

import org.springframework.stereotype.Repository;
import panoplie.orgoal.todo.Todo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryTodoRepository implements TodoRepository {

    private final List<Todo> repository = new ArrayList<>();

    @Override
    public List<Todo> findAll() {
        return repository;
    }

    @Override
    public Todo findTodo(int id) {
        for (int i = 0; i < repository.size(); i++) {

        }
        return null;
    }

    @Override
    public void save(Todo todo) {
        repository.add(todo);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean finish(int id) {
        return false;
    }


}

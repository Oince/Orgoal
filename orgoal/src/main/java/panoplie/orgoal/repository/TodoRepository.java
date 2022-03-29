package panoplie.orgoal.repository;

import panoplie.orgoal.todo.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
    Todo findTodo(int id);
    void save(Todo todo);
    void delete(int id);
    boolean finish(int id);

}

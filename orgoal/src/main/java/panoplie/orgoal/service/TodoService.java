package panoplie.orgoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import panoplie.orgoal.repository.TodoRepository;
import panoplie.orgoal.todo.Todo;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void add(Todo todo) {
        todoRepository.save(todo);
    }

    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    public int delete(int id) {
        return 0;
    }

    public int finish(int id) {
        return 0;
    }
}

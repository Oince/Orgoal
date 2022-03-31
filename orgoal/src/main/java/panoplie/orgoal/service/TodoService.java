package panoplie.orgoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import panoplie.orgoal.repository.TodoRepository;
import panoplie.orgoal.domain.Todo;

import java.util.List;

//서비스를 제공하는 객체
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //저장소에 추가
    public void add(Todo todo) {
        todoRepository.save(todo);
    }

    //전체 todolist 가져오기
    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    //todo 완료 하기
    public Todo finish(Long id) throws NullPointerException {
        Todo todo = todoRepository.findById(id);
        todo.setFinished(true);
        todoRepository.update(id, todo);
        return todo;
    }

    //todo 삭제
    public void delete(Long id) throws NullPointerException {
        todoRepository.delete(id);
    }

}

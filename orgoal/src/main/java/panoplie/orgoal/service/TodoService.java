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
    public String finish(Long id) {
        try {
            todoRepository.updateFinished(id);
        }
        //NullPointerException이 터질 경우 해당 메세지 리턴
        catch (NullPointerException e) {
            return "해당 데이터가 존재하지 않음";
        }
        //정상적인 경우 OK리턴
        return "OK";
    }

    //미구현
    public int delete(Long id) {
        return 0;
    }

}

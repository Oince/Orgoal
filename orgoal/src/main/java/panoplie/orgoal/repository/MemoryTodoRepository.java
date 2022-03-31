package panoplie.orgoal.repository;

import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//데이터를 다루는 객체
@Repository
public class MemoryTodoRepository implements TodoRepository {

    //Map 형태로 저장
    private static final Map<Long, Todo> repository = new HashMap<>();
    //Todo의 id값
    private static Long id = 0L;

    //전체 데이터를 List형태로 반환
    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(repository.values());
    }

    //id값을 통해 하나의 Todo 반환
    @Override
    public Todo findById(Long id) throws NullPointerException{
        return repository.get(id);
    }

    //Todo 객체 저장
    @Override
    public void save(Todo todo) {
        todo.setId(++id);
        repository.put(todo.getId(), todo);
    }

    //id에 해당하는 todo 객체 삭제
    @Override
    public void delete(Long id) throws NullPointerException{
        repository.remove(id);
    }

    //id에 해당하는 todo를 newTodo로 교체
    @Override
    public void update(Long id, Todo newTodo) {
        repository.put(id, newTodo);
    }


}

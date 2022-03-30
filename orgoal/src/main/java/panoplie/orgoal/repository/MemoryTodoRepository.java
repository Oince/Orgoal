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

    //미구현
    @Override
    public void delete(Long id) {

    }

    //patch 요청시 해당하는 todo를 찾아서 값 수정
    //해당하는 id값이 없을 때 NullPointerException이 발생하므로 TodoService 객체에 넘겨서 처리
    @Override
    public void updateFinished(Long id) throws NullPointerException {
        Todo todo = repository.get(id);
        todo.setFinished(true);
        repository.put(id, todo);
    }


}

package panoplie.orgoal.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import panoplie.orgoal.repository.MemoryTodoRepository;
import panoplie.orgoal.todo.Todo;

import java.util.List;


class TodoServiceTest {

    MemoryTodoRepository todoListRepository = new MemoryTodoRepository();
    TodoService todoService = new TodoService(todoListRepository);

    @Test
    @DisplayName("asd")
    void getTodoList() {
        Todo todo1 = new Todo(1, "test1", false);
        Todo todo2 = new Todo(2, "test2", false);
        Todo todo3 = new Todo(3, "test3", false);

        todoListRepository.save(todo1);
        todoListRepository.save(todo2);
        todoListRepository.save(todo3);

        List<Todo> todoList = todoService.getTodoList();

        Todo todo = todoList.get(0);

        Assertions.assertThat(todo.getText()).isEqualTo(todo1.getText());


    }


}
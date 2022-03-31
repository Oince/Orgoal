package panoplie.orgoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import panoplie.orgoal.service.TodoService;
import panoplie.orgoal.domain.Todo;
import panoplie.orgoal.domain.TodoForm;

import java.util.List;

//json 형식으로 응답한다는 뜻
@RestController
//  /todo 주소에 매핑
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // /todo에 get요청
    @GetMapping
    //http 상태 코드 200(Ok)로 설정
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    // /todo에 post요청
    @PostMapping
    //상태 코드 201로 설정
    @ResponseStatus(HttpStatus.CREATED)
    public void addTodo(/*자동으로 요청 바디를 json 형식으로 변환*/@RequestBody TodoForm todoForm) {
        //todo 객체 설정
        Todo todo = new Todo(todoForm.getText(), false);
        todoService.add(todo);
    }

    // /todo에 patch요청
    @PatchMapping
    public ResponseEntity<Todo> patchTodo(@RequestBody Todo todo) {

        Todo finish;
        try {
            finish = todoService.finish(todo.getId());
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(finish, HttpStatus.CREATED);
    }

    // /todo에 delete요청
    @DeleteMapping
    public ResponseEntity<String> deleteTodo(@RequestBody Todo todo) {
        try {
            todoService.delete(todo.getId());
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

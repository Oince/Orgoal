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

    // /todo 에 get요청
    @GetMapping
    //http 상태 코드 200(Ok)로 설정
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    // /todo 에 post요청
    @PostMapping
    //상태 코드 201로 설정
    @ResponseStatus(HttpStatus.CREATED)
    public void addTodo(@RequestBody TodoForm todoForm) {
        //todo 객체 설정
        Todo todo = new Todo(todoForm.getText(), false);
        todoService.add(todo);
    }

    // /todo 에 patch요청
    @PatchMapping
    public ResponseEntity<String> patchTodo(@RequestBody Todo todo) {
        String message = todoService.finish(todo.getId());
        // message가 OK면 상태 코드를 201로 해서 응답
        if (message.equals("OK")) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        // OK가 아니면 404로 응답
        else {
            String errMessage = "404 Error" + '\n' + message;
            return new ResponseEntity<>(errMessage, HttpStatus.BAD_REQUEST);
        }
    }


}

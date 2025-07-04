package com.anish.todo_management.controller;

import com.anish.todo_management.dto.TodoDto;
import com.anish.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;


    //Build Add todo REST API
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //Build GET todo REST API
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id){
        TodoDto todoDto=todoService.getTodo(id);

        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

    //Build GET ALL todo REST API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos=todoService.getAllTodos();
        return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    //Build UPDATE Todo REST API
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long id){
        TodoDto updatedTodo=todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(updatedTodo);

    }

    //Build DELETE Todo REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo Deleted Successfully");
    }

    //Build complete Todo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id){
        TodoDto updatedTodo=todoService.completeTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }

    //Build Incomplete Todo REST API
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id){
        TodoDto updatedTodo=todoService.inCompleteTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }


}

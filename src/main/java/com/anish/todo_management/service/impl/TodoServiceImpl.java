package com.anish.todo_management.service.impl;

import com.anish.todo_management.dto.TodoDto;
import com.anish.todo_management.entity.Todo;
import com.anish.todo_management.repository.TodoRepository;
import com.anish.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //convert todoDto to todoJPA entity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription((todoDto.getDescription()));
        todo.setCompleted(todoDto.isCompleted());

        //todo JPA entity

        Todo savedTodo=todoRepository.save(todo);

        //convert saved todo JPA entity into TodoDto object

        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodoDto.isCompleted());

        return savedTodoDto;
    }
}

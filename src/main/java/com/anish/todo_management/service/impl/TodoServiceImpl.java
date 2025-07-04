package com.anish.todo_management.service.impl;

import com.anish.todo_management.dto.TodoDto;
import com.anish.todo_management.entity.Todo;
import com.anish.todo_management.exception.ResourceNotFound;
import com.anish.todo_management.repository.TodoRepository;
import com.anish.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //convert todoDto to todoJPA entity
        Todo todo = modelMapper.map(todoDto,Todo.class);

        //todo JPA entity
        Todo savedTodo=todoRepository.save(todo);

        //convert saved todo JPA entity into TodoDto object
        TodoDto savedTodoDto = modelMapper.map(savedTodo,TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {

        Todo todo=todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Todo not found with given id"));

        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos=todoRepository.findAll();

        return todos.stream().map((todo -> modelMapper.map(todo, TodoDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        Todo todo=todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Given Todo id not found"));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo=todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {

        Todo todo=todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Given I d not found"));

        todoRepository.deleteById(id);
    }
}

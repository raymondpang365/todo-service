package com.raymondpang365.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/users/{userId}/top-across-all-lists")
    public ResponseEntity<List<TodoItemDto>> httpGetTopTodosAcrossAllListsForAUser(
            @PathVariable("userId") String userId
    ) {
        List<TodoItemDto> items = todoService.getTopTodosAcrossAllListsForAUser(userId);
        return ResponseEntity.ok(items);
    }
}
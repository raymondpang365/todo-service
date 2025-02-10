package com.raymondpang365.todo;


import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Getter
    private final Integer topResultsSize = 10;

    /**
     * Fetch all items for a given user from TodoRepository,
     * then sort in the API service, and return the top 10 by priority score.
     */
    public List<TodoItemDto> getTopTodosAcrossAllListsForAUser(String userId) {

        List<TodoItemEntity> allItems = todoRepository
                .findByUserId(
                        new ObjectId(userId)
                );

        /*
        Sorting is done in memory (API service)
        */
        List<TodoItemEntity> sorted = allItems.stream()
                .sorted(
                        Comparator
                                .comparing(TodoItemEntity::getScore)
                                .reversed())
                .limit(topResultsSize)
                .toList();

        /*
        Convert DB Entity to DTO
        */
        return sorted.stream().map(entity -> new TodoItemDto(
                entity.getId().toHexString(),
                entity.getName(),
                entity.getDescription(),
                entity.getScore()
        )).collect(Collectors.toList());
    }
}
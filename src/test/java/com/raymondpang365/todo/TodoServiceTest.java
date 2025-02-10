package com.raymondpang365.todo;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.print.attribute.standard.JobKOctets;
import java.util.ArrayList;
import java.util.List;

import static com.raymondpang365.todo.ObjectIdUtils.generateRandomObjectIdString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoServiceTest {

    @MockBean
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @Test
    void testGetTop10TodoItems() {
        List<TodoItemEntity> mockData = new ArrayList<>();

        // Create 15 items with various scores
        String userId = "507f1f77bcf86cd799439011";
        for (int i = 1; i <= 15; i++) {
            TodoItemEntity e = new TodoItemEntity();
            e.setId(new ObjectId(userId));
            String objectIdString = generateRandomObjectIdString();
            e.setListId(new ObjectId(objectIdString));
            e.setUserId(new ObjectId(userId));
            e.setName("Task " + i);
            e.setDescription("Description " + i);
            e.setScore((float) i); // Score increments from 1..15
            mockData.add(e);
        }

        when(todoRepository.findByUserId(new ObjectId(userId))).thenReturn(mockData);

        Integer topResultsSize = todoService.getTopResultsSize();

        List<TodoItemDto> top10 = todoService.getTopTodosAcrossAllListsForAUser(userId);
        assertEquals(
                topResultsSize, // 10
                top10.size(),
                String.format("Should only retrieve %d", topResultsSize)
        );
        assertEquals(
                15,
                top10.get(0).getScore(),
                "Highest score item should be first");
        // Next highest
        assertEquals(
                14,
                top10.get(1).getScore(),
                "Next highest should be 14"
        );
    }
}
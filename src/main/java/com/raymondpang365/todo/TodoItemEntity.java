package com.raymondpang365.todo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document(collection = "todo")
@Data
public class TodoItemEntity {

    @MongoId
    private ObjectId id;
    private ObjectId userId;
    private ObjectId listId;

    /*
    Use Float instead of integer to achieve O(1) time complexity by a midpoint value
    in case user wants to insert an item with a priority score relative to two adjacent items
    */
    private Float score;
    private String name;
    private String description;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}

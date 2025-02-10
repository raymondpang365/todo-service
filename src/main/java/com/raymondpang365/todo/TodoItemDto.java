package com.raymondpang365.todo;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TodoItemDto {
    // Use String to support 24 characters UUID in Mongo ObjectId
    private String id;
    private String name;
    private String description;

    /*
     - Use Float instead of integer to achieve O(1) time complexity by a midpoint value
    in case user wants to insert an item with a priority score relative to two adjacent items
     - Larger value denotes higher priority
     */
    private Float score;
}

package com.raymondpang365.todo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<TodoItemEntity, String> {

    /**
     * We do not need to iterate over each DB instance manually;
     * MongoDB cluster handles that automatically behind one DB call.
     */
    List<TodoItemEntity> findByUserId(ObjectId userId);

}
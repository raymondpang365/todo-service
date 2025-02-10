package com.raymondpang365.todo;

import org.bson.types.ObjectId;

public class ObjectIdUtils {

    /**
     * Generates a new random ObjectId and returns its string representation.
     *
     * @return A 24-character hexadecimal string representation of the ObjectId
     */
    public static String generateRandomObjectIdString() {
        ObjectId objectId = new ObjectId(); // Generate a random ObjectId
        return objectId.toHexString();      // Convert to a 24-character hexadecimal string
    }

    public static void main(String[] args) {
        // Test the function
        String objectIdString = generateRandomObjectIdString();
        System.out.println("Generated ObjectId String: " + objectIdString);
    }
}
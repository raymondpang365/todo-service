// seed.js
// MongoDB connection string (edit as needed)
const uri = "mongodb://localhost:27017";

// Database and collection names
const dbName = "tododb"; // Replace with your database name
const collectionName = "todo"; // Replace with your collection name

// Hardcoded user ObjectIds
const user1Id = ObjectId("63fdfc8b21a3b1e54d2c3f99");
const user2Id = ObjectId("63fdfc8c4b3b1f12a2c3d8ab");

// Number of records to insert
const totalRecords = 30;

// Connect to MongoDB
const client = new Mongo(uri);
const db = client.getDB(dbName);

// Check if the collection already has data
if (db[collectionName].countDocuments() > 0) {
    print("Seeding skipped: Collection already contains data.");
} else {
    print("Starting database seeding...");

    // Array to hold the documents
    const documents = [];

    for (let i = 1; i <= totalRecords; i++) {
        const userId = i <= totalRecords / 2 ? user1Id : user2Id; // First 15 records for User 1, next 15 for User 2
        const listId = ObjectId(); // Generate a random ObjectId for listId

        const document = {
            listId: listId,
            userId: userId,
            name: `Seeded Task ${i}`,
            description: `Generated task #${i}`,
            score: i, // Incremental score from 1 to 30
            status: "NEW",
        };

        documents.push(document);
    }

    // Insert all documents into the collection
    db[collectionName].insertMany(documents);

    print("Database seeding completed successfully.");
}

// Close the connection
client.close();
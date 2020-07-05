package subscriber.repo;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import subscriber.models.Subscription;
import static com.mongodb.client.model.Filters.*;

public class SubscriptionRepo {
    MongoClientURI connectionString;
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    Gson gson;

    public SubscriptionRepo() {
        this.connectionString = new MongoClientURI("");
        this.mongoClient = new MongoClient(connectionString);
        this.database = mongoClient.getDatabase("oi-workflow-exp");
        this.collection = database.getCollection("subscription");
        this.gson = new Gson();
    }

    public void save(Subscription subscription) {
        collection.insertOne(toDoc(subscription));
    }

    public void save(String subscriptionJson) {
        collection.insertOne(toDoc(convertJsonToSubscription(subscriptionJson)));
    }

    public void update(Subscription subscription) {
        this.collection.updateOne(eq("fhShipmentId", subscription.fhShipmentId), new Document("$set", toDoc(subscription)));
    }

    public Subscription findBySubscriptionId(String key) {
        Document result = this.collection.find(eq("subscriptionId", key)).first();

        return this.gson.fromJson(result.toJson(), Subscription.class);
    }

    private Subscription convertJsonToSubscription(String subscriptionJson) {
        return this.gson.fromJson(subscriptionJson, Subscription.class);
    }

    private static Document toDoc(Subscription subscription) {
        return new Document("fhShipmentId", subscription.fhShipmentId)
                .append("subscriptionKey", subscription.subscriptionKey)
                .append("type", subscription.type)
                .append("carrierId", subscription.carrierId)
                .append("subscriptionId", subscription.subscriptionId)
                .append("isLegit", subscription.isLegit);
    }
}

package hu.taviro.observatory.backend.application.migrations;

import java.util.Collections;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


@ChangeLog
public class MongoChangeLog {

    @ChangeSet(order = "001", id = "initialStation", author = "aberkecz")
    public void initialStation(MongoDatabase mongoDatabase) {
        mongoDatabase.createCollection("stations");
        MongoCollection<Document> stationsCollection = mongoDatabase.getCollection("stations");
        Document initialStation = new Document("name", "Living room station");
        initialStation.append("stationData", Collections.emptyList());
        stationsCollection.insertOne(initialStation);
    }
}

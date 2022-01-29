package kz.pompei.mongo.core.env;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import kz.pompei.mongo.core.env.codecs.EnumCodecRegistry;
import kz.pompei.mongo.core.env.codecs.TimeZoneCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class MongoConnection {

  private final MongoClient   mongoClient;
  private final MongoDatabase databaseAsd;

  public MongoConnection() {

    PojoCodecProvider.Builder pojoBuilder = PojoCodecProvider.builder();
    pojoBuilder.automatic(true);

    PojoCodecProvider pojoCodecProvider = pojoBuilder.build();

    CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();

    EnumCodecRegistry enumCodecRegistry = new EnumCodecRegistry();

    CodecRegistry finishedCodecRegistry = CodecRegistries.fromRegistries(
      TimeZoneCodec.REGISTRY,
      codecRegistry,
      fromProviders(pojoCodecProvider),
      enumCodecRegistry
    );

    ConnectionString connectionString = new ConnectionString("mongodb://localhost:11011");

    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                                                                 .applyConnectionString(connectionString)
                                                                 .codecRegistry(finishedCodecRegistry)
                                                                 .build();

    mongoClient = MongoClients.create(mongoClientSettings);

    databaseAsd = mongoClient.getDatabase("asd");

  }

  public MongoDatabase getDatabaseAsd() {
    return databaseAsd;
  }

  public void close() {
    mongoClient.close();
  }
}

package kz.pompei.mongo.core.env;

import com.mongodb.client.MongoCollection;
import kz.pompei.mongo.core.mongo_model.ClientDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MongoAccess {

  private final MongoConnection mongoConnection;

  public MongoCollection<ClientDto> client() {
    return mongoConnection.getDatabaseAsd().getCollection("Client", ClientDto.class);
  }

  public void close() {
    mongoConnection.close();
  }
}

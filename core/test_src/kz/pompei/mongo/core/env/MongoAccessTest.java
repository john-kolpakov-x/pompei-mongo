package kz.pompei.mongo.core.env;

import kz.greetgo.util.RND;
import kz.pompei.mongo.core.mongo_model.ClientDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MongoAccessTest {
  MongoAccess mongoAccess;

  @BeforeClass
  public void openConnection() {
    System.out.println("I1hjJfNyG0 :: Open Connection");
    mongoAccess = new MongoAccess(new MongoConnection());
  }

  @AfterClass
  public void closeConnection() {
    System.out.println("OkjMy54MXd :: CLOSE Connection");
    mongoAccess.close();
  }

  @Test(invocationCount = 30)
  public void testName() {

    var clientDto = new ClientDto();
    clientDto.id      = Ids.generate();
    clientDto.surname = RND.str(13);
    clientDto.name    = RND.str(13);

    mongoAccess.client().insertOne(clientDto);
  }

}

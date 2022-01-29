package kz.pompei.mongo.core.env;

import java.security.SecureRandom;
import java.util.Base64;

public class Ids {

  private final static ThreadLocal<SecureRandom> rnd = ThreadLocal.withInitial(SecureRandom::new);

  public static String generate() {
    byte[] bytes = new byte[12];
    rnd.get().nextBytes(bytes);
    return Base64.getEncoder().encodeToString(bytes).replace('/', C1).replace('+', C2);
  }

  private static final char C1 = '~';
  private static final char C2 = '@';
}

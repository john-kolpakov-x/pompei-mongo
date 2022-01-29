package kz.pompei.mongo.core.env.codecs;

import java.util.concurrent.ConcurrentHashMap;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;

@SuppressWarnings("rawtypes")
public class EnumCodecRegistry implements CodecRegistry {

  private final ConcurrentHashMap<Class, Codec> codecMap = new ConcurrentHashMap<>();

  @Override
  @SuppressWarnings("unchecked")
  public Codec get(Class clazz) {

    if (!isAnEnum(clazz)) {
      return null;
    }

    return codecMap.computeIfAbsent(clazz, EnumCodec::new);

  }

  private boolean isAnEnum(Class clazz) {
    if (clazz.isEnum()) {
      return true;
    }

    var superclass = clazz.getSuperclass();
    if (superclass == null) {
      return false;
    }

    return superclass.isEnum();
  }

}

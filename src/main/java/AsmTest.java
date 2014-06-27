import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Supplier;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

class AsmTest implements Serializable {

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    Serializable someLambda = (Serializable & Supplier<BiMap<String, String>>) () -> {
      HashBiMap<String, String> biMap = HashBiMap.create();
      biMap.put("Hello", "world!");
      return biMap;
    };

    Supplier<BiMap<String, String>> deserialized = (Supplier<BiMap<String, String>>) deserialize(serialize(someLambda));
    BiMap<String, String> biMap = deserialized.get();
    System.out.println(biMap);
  }

  public static byte[] serialize(Serializable obj) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
    ObjectOutputStream out = new ObjectOutputStream(baos);
    out.writeObject(obj);
    return baos.toByteArray();
  }

  public static Object deserialize(byte[] objectData) throws IOException, ClassNotFoundException {
    ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
    ObjectInputStream in = new ObjectInputStream(bais);
    return in.readObject();
  }
}
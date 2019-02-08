import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomerDeserializer implements Deserializer<Customer>{
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Customer deserialize(String s, byte[] bytes) {
        return null;
    }

    @Override
    public void close() {

    }
}

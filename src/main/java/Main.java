import org.apache.kafka.clients.producer.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        String resourceName = "application.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        Producer<String, Customer> producer = null;

        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
            producer = new KafkaProducer<String, Customer>(props);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProducerRecord<String, Customer> record = new ProducerRecord<>("test", "Precision Products", new Customer(1, "Marcos"));

        //sending a message synchronously
        try {
            Long timeBefore = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e != null) {
                            e.printStackTrace();
                        } else {
                            System.out.println(recordMetadata.toString());
                        }
                    }
                });
            }
            Long timeAfter = System.currentTimeMillis();
            Long roundTripTime = timeAfter - timeBefore;
            System.out.println("Roundtrip time: " + roundTripTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}

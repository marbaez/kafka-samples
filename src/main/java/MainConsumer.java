import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class MainConsumer {
    public static void main(String[] args) throws IOException {
        String resourceName = "consumer.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        Consumer<String, String> consumer = null;

        InputStream resourceStream = loader.getResourceAsStream(resourceName);
        props.load(resourceStream);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        consumer = new KafkaConsumer<String, String>(props);


//        consumer.subscribe(Collections.singletonList("test"));
        consumer.subscribe(Collections.singletonList("messages"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {

            }
        });

        try {
            while (true) {
                ConsumerRecords<String, String> records  = consumer.poll(Duration.ofSeconds(10));
                for (ConsumerRecord<String,String> record: records) {
                    System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n",record.topic(), record.partition(), record.offset(),record.key(), record.value());
                }

                try {
                    consumer.commitSync();
                    System.out.println("esuchando mensajes");
                } catch (CommitFailedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            consumer.close();
        }



    }
}

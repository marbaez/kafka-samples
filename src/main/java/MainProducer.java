import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class MainProducer {

    private class DemoProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            System.out.println("Ha llegado a un callback");
            System.out.println(recordMetadata.toString());
            if (e!=null) {
                e.printStackTrace();
            }
        }
    }

    public void produceContent() throws InterruptedException {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);

        //AQUÍ ESTÁN LOS TRES TIPOS DE COMUNICACIÓN DE MENSAJES QUE SE PUEDEN TENER

        //fire and forget
        ProducerRecord<String, String> record = new ProducerRecord<>("test", "mensaje", "mensaje desde java");
        producer.send(record);


        //synchronous send
        record = new ProducerRecord<>("test", "mensaje", "mensaje desde java sincrono");
        try {
            producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //asynchronous send
        record = new ProducerRecord<>("test", "mensaje", "mensaje desde java asincrono");
        Future response = producer.send(record, new DemoProducerCallback());
        while (!response.isDone()) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MainProducer mp = new MainProducer();
        mp.produceContent();
    }
}

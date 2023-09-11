package org.glauber;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrder {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        //var value = "OrderId,UserId,price";
        Callback callBack = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
            }
            System.out.printf("Message sent: %s:::partition %s / offset %s / timestamp %s",
                    data.topic(), data.partition(), data.offset(), data.timestamp());
        };

        var order = "9,2,30.0";
        var newOrderRecord = new ProducerRecord<>("STORE_NEW_ORDER", UUID.randomUUID().toString(), order);
        var email = "Thanks! We are processing your order";
        var newEmailRecord = new ProducerRecord<>("STORE_SEND_EMAIL", UUID.randomUUID().toString(), email);

        producer.send(newOrderRecord, callBack).get();
        producer.send(newEmailRecord, callBack).get();
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}

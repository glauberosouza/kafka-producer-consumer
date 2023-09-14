package org.glauber;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;

public class FraudDetectorService {
    public static void main(String[] args) throws IOException {
        try (var consumer = new KafkaService(
                FraudDetectorService.class.getSimpleName(),
                "STORE_NEW_ORDER",
                new FraudDetectorService()::parse)) {
            consumer.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("----------------------------------------");
        System.out.println("Sending email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            //noinspection BusyWait
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }
}
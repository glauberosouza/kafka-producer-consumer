package org.glauber;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {
    public static void main(String[] args) {
        var emailService = new EmailService();
        try (var service = new KafkaService<>(
                EmailService.class.getSimpleName(), "STORE_SEND_EMAIL", emailService::parse, String.class)) {
            service.run();
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
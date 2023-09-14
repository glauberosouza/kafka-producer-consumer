package org.glauber;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrder {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try (var dispatcher = new KafkaDispatcher()) {
            var order = "12,5,100.0";
            dispatcher.send("STORE_NEW_ORDER", UUID.randomUUID().toString(), order);
            var email = "Thanks! We are processing your order";
            dispatcher.send("STORE_SEND_EMAIL", UUID.randomUUID().toString(), email);
        }
    }
}
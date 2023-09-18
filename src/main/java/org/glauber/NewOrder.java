package org.glauber;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrder {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try (var dispatcher = new KafkaDispatcher<Order>()) {
            var order = new Order(UUID.randomUUID().toString(), "1", BigDecimal.valueOf(1));
            dispatcher.send("STORE_NEW_ORDER", UUID.randomUUID().toString(), order);
        }

        try(var dispatcher = new KafkaDispatcher<String>()){
            var email = "Thanks! We are processing your order";
            dispatcher.send("STORE_SEND_EMAIL", UUID.randomUUID().toString(), email);
        }

    }
}
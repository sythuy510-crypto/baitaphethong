package com.example.service;

import com.example.dto.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@ConditionalOnProperty(name = "app.broker.enabled", havingValue = "false", matchIfMissing = true)
public class InProcessOrderProducer {
    private final ThreadPoolExecutor executor;
    
    @Autowired
    private OrderProcessingService orderProcessingService;

    public InProcessOrderProducer() {
        this.executor = new ThreadPoolExecutor(
            8,
            8,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000)
        );
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        executor.submit(() -> orderProcessingService.processOrder(event));
    }
}

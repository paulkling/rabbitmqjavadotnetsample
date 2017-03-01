package hello;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
            ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
         for(int i=1; i<1000000000; i++) {

            System.out.println("Sending messag1111e...");
            rabbitTemplate.convertAndSend(Application.queueName, "Hello from RabbitMQ!");
        
        
        
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
            Thread.sleep(5000);
        }
        context.close();
    }

}

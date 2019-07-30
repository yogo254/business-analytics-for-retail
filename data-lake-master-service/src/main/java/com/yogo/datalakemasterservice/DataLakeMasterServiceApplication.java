package com.yogo.datalakemasterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.datalakemasterservice.entity.OrderTransaction;
import com.yogo.datalakemasterservice.entity.PaymentTransaction;
import com.yogo.datalakemasterservice.repo.CustomerOrderRepo;
import com.yogo.datalakemasterservice.repo.PaymentRepo;
import com.yogo.datalakemasterservice.sevices.OrderItemService;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.concurrent.*;


@SpringBootApplication
public class DataLakeMasterServiceApplication implements ApplicationRunner {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    private AdminClient adminClient;

    @Autowired
    private CustomerOrderRepo orderRepo;
    @Autowired
    private OrderItemService itemService;
    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ObjectMapper mapper;



    public static void main(String[] args) {
        SpringApplication.run(DataLakeMasterServiceApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        adminClient.createTopics(Arrays.asList(new NewTopic("orders",1, (short) 1),
                new NewTopic("payments",1, (short) 1)));


        BlockingQueue<OrderTransaction> buffer=new LinkedBlockingQueue<>(100);
        BlockingQueue<PaymentTransaction>paymentsBuffer=new LinkedBlockingQueue<>(100);

        orderRepo.findAll()
                .repeat()
                .delayElements(Duration.ofMillis(500), Schedulers.elastic())
                .map(e->new OrderTransaction(e,itemService.getCustomer(e.getCustomerId()),itemService.getOrderItems(e.getOrderId())))
                .subscribe(e->buffer.offer(e));

        paymentRepo.findAll()
                .repeat()
                .delayElements(Duration.ofSeconds(3),Schedulers.elastic())
                .map(e->new PaymentTransaction(orderRepo.findById(e.getOrderId()).block(),itemService.getCustomer(e.getOrderId()),e))
                .subscribe(e->paymentsBuffer.offer(e));



        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                kafkaTemplate.send("orders",serialize(buffer));
                buffer.clear();

            }
        };

        TimerTask paymentTask= new TimerTask() {
            @Override
            public void run() {
                kafkaTemplate.send("payments",serialize(paymentsBuffer));
                paymentsBuffer.clear();

            }
        };
        ScheduledExecutorService executorService= Executors
                .newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(task,30,10, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(paymentTask,40,20,TimeUnit.SECONDS);










    }
    public String serialize(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
    @Bean
    ObjectMapper getMapper(){
        return new ObjectMapper();
    }
}


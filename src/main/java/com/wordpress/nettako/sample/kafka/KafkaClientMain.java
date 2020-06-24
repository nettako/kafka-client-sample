package com.wordpress.nettako.sample.kafka;

import com.wordpress.nettako.sample.kafka.client.ConsumerCreator;
import com.wordpress.nettako.sample.kafka.client.ProducerCreator;

public class KafkaClientMain {

    public static void main(String args[]){
        if("producer".equals(args[0])){
            ProducerCreator.runProducer();
        }

        if("consumer".equals(args[0])){
            ConsumerCreator.runConsumer();
        }
    }
}

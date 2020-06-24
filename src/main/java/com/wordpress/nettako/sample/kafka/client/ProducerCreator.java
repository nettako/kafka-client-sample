package com.wordpress.nettako.sample.kafka.client;

import com.wordpress.nettako.sample.kafka.config.ConfigYml;
import com.wordpress.nettako.sample.kafka.data.JSONSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.json.JSONObject;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerCreator {
    public static Producer<Long, JSONObject> createProducer() {
        Properties props = new Properties();
        props.put("zookeeper.connect", ConfigYml.getConfig().getZooKeeperConnect());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ConfigYml.getConfig().getKafkaBrokers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, ConfigYml.getConfig().getClientId());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JSONSerializer.class.getName());
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        return new KafkaProducer<>(props);
    }

    public static void runProducer() {
        Producer<Long, JSONObject> producer = ProducerCreator.createProducer();
        for (int index = 0; index < ConfigYml.getConfig().getMessageCount(); index++) {
            JSONObject data = new JSONObject();
            data.put("id", index);
            data.put("desc", "this is data number "+index);
            ProducerRecord<Long, JSONObject> record = new ProducerRecord(ConfigYml.getConfig().getTopicName(), data);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            }
            catch (ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
            catch (InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}

package com.wordpress.nettako.sample.kafka.data;

import org.apache.kafka.common.serialization.Serializer;
import org.json.JSONObject;

import java.util.Map;

public class JSONSerializer implements Serializer<JSONObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, JSONObject data) {
        byte[] retVal = null;
        try {
            retVal = data.toString().getBytes();
        } catch (Exception exception) {
            System.out.println("Error in serializing object"+ data);
        }
        return retVal;
    }

    @Override
    public void close() {
    }
}
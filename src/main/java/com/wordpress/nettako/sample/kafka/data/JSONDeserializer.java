package com.wordpress.nettako.sample.kafka.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.json.JSONObject;

import java.util.Map;

public class JSONDeserializer implements Deserializer<JSONObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    @Override
    public JSONObject deserialize(String topic, byte[] data) {
        JSONObject object = null;
        try {
            object = new JSONObject(new String(data));
        } catch (Exception exception) {
            System.out.println("Error in deserializing bytes "+ exception);
        }
        return object;
    }
    @Override
    public void close() {
    }
}

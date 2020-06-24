package com.wordpress.nettako.sample.kafka.config;

import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

@Data
public class ConfigYml {
    private String kafkaBrokers;
    private String zooKeeperConnect;
    private Integer messageCount;
    private String clientId;
    private String topicName;
    private String groupIdConfig;
    private Integer maxNoMessageFoundCount;
    private String offsetResetLatest;
    private String offsetResetEarlier;
    private Integer maxPollRecords;

    public static ConfigYml getConfig(){
        ConfigYml configYml = new ConfigYml();
        InputStream inputStream = configYml.getClass().getClassLoader().getResourceAsStream("config.yml");
        Yaml yaml = new Yaml();
        ConfigYml configYaml = yaml.load(inputStream);
        return configYaml;
    }
}

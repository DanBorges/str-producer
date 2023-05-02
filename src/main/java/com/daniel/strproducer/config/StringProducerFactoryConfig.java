package com.daniel.strproducer.config;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class StringProducerFactoryConfig {
	
    private final KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory producerFactory() {
		var configs = new HashMap<String, Object>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}

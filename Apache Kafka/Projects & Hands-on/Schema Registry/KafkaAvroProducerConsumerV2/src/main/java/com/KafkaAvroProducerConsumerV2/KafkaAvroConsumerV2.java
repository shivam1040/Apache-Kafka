package com.KafkaAvroProducerConsumerV2;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.example.Customer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public class KafkaAvroConsumerV2 {
	public static void main(String[] args) {
		Properties properties=new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("group.id", "consumer-avro");
		properties.setProperty("enable.auto.commit", "false");
		properties.setProperty("auto.offset.reset", "earliest");
		properties.setProperty("key.deserializer",StringDeserializer.class.getName());
		properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
		//properties.setProperty("schema.registry.url", "http://localhost:8081");
		properties.setProperty("specific.avro.reader", "true"); //making schema read specific record type
		
		properties.setProperty("schema.registry.url", "https://localhost:8082");
        properties.setProperty("schema.registry.ssl.truststore.location", "/Users/Z00B26K/sec/truststore.jks");
        properties.setProperty("schema.registry.ssl.truststore.password", "admin123");
        properties.setProperty("schema.registry.ssl.keystore.location", "/Users/Z00B26K/sec/keystore.jks");
        properties.setProperty("schema.registry.ssl.keystore.password", "admin123");
        properties.setProperty("schema.registry.ssl.key.password", "admin123");
        properties.setProperty("ssl.protocol", "TLSv1.2");
		
		
		KafkaConsumer<String, Customer> kafkaConsumer=new KafkaConsumer<String, Customer>(properties);
		String topic="customer-avro";
		
		//notice how v1 producer can be consumed by v2 consumer due to schema evolution
		
		kafkaConsumer.subscribe(Collections.singleton(topic)); //means subscribing to one topic
		
		System.out.println("waiting data");
		while(true) {
			ConsumerRecords<String, Customer> records=kafkaConsumer.poll(500); //polling in intervals of 500ms
			for(ConsumerRecord<String, Customer> record:records) {
				Customer customer=record.value();
				System.out.println(customer);
			}
			kafkaConsumer.commitSync();
			
		}
	}
}

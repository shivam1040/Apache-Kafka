package com.KafkaAvroProducer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.example.Customer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public class KafkaAvroConsumer {
	public static void main(String[] args) {
		Properties properties=new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("group.id", "consumer-avro-1"); //using different so that i can recieve messages in both versions of consumer
		properties.setProperty("enable.auto.commit", "false");
		properties.setProperty("auto.offset.reset", "earliest");
		properties.setProperty("key.deserializer",StringDeserializer.class.getName());
		properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
		properties.setProperty("schema.registry.url", "http://localhost:8081");
		properties.setProperty("specific.avro.reader", "true"); //making schema read specific record type
		
		
		KafkaConsumer<String, Customer> kafkaConsumer=new KafkaConsumer<String, Customer>(properties);
		String topic="customer-avro";
		
		
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
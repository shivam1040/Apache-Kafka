package com.KafkaAvroProducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.example.Customer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//kafka avro producer, creating schema in registry and producing avro bytes to broker
        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "10");
        properties.setProperty("key.serializer",org.apache.kafka.common.serialization.StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://localhost:8081");
        
        KafkaProducer<String, Customer> kafkaProducer=new KafkaProducer<String, Customer>(properties);
        Customer customer=Customer.newBuilder()
        		.setFirstName("A")
        		.setAge(121423)
        		.setHeight(1F) //notice how phone which is present is v2 schema of customer is not sent from here but consumer using v2 schema will still show the recieved msgs from v1 producer using schema evolution, so in this case default value of phone will be appendedn to final message, check v2 schema definition
        		.build();
        String topic="customer-avro";
        ProducerRecord<String, Customer> producerRecord=new ProducerRecord<String, Customer>(topic, customer);
        
        kafkaProducer.send(producerRecord, new Callback() {
			
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if(exception==null) {
					System.out.println("success");
					System.out.println(metadata.toString()); //prints offset, partitions
				}
				else
					System.out.println("error");
			}
		});
        kafkaProducer.flush(); //garbage collection
        kafkaProducer.close();
    }
}

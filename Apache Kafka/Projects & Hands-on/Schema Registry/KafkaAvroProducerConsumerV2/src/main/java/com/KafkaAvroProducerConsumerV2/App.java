package com.KafkaAvroProducerConsumerV2;

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
        //properties.setProperty("schema.registry.url", "http://localhost:8081");
        
        //configuration for mTLS authentication
        properties.setProperty("schema.registry.url", "https://localhost:8082");
        properties.setProperty("schema.registry.ssl.truststore.location", "/Users/Z00B26K/sec/truststore.jks");
        properties.setProperty("schema.registry.ssl.truststore.password", "admin123");
        properties.setProperty("schema.registry.ssl.keystore.location", "/Users/Z00B26K/sec/keystore.jks");
        properties.setProperty("schema.registry.ssl.keystore.password", "admin123");
        properties.setProperty("schema.registry.ssl.key.password", "admin123");
        properties.setProperty("ssl.protocol", "TLSv1.2");
        
        //this producer is producing using v2 schema of customer but the consumer consuming using v1 of customer will also recieve the data due to schema evolution and full compatibility
        KafkaProducer<String, Customer> kafkaProducer=new KafkaProducer<String, Customer>(properties);
        Customer customer=Customer.newBuilder()
        		.setFirstName("A")
        		.setAge(13123)
        		.setHeight(2f)
        		.setPhone("1") //notice how phone is sent from here from consumer v1 is not recieving it
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

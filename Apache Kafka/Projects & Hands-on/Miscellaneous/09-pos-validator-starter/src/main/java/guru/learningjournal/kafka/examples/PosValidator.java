package guru.learningjournal.kafka.examples;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import guru.learningjournal.kafka.examples.serde.JsonDeserializer;
import guru.learningjournal.kafka.examples.types.PosInvoice;

public class PosValidator {
	private static final Logger logger=LogManager.getLogger();
	public static void main(String[] args) {
		Properties properties=new Properties();
		properties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		//properties.put(JsonDeser)
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, AppConfigs.groupID);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		KafkaConsumer<String, PosInvoice> consumer=new KafkaConsumer<String, PosInvoice>(properties);
		consumer.subscribe(Arrays.asList(AppConfigs.sourceTopicNames));
		
		Properties producerProps=new Properties();
		producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		KafkaProducer<String, PosInvoice> producer=new KafkaProducer<String, PosInvoice>(producerProps);
		
		while(true) {
			ConsumerRecords<String, PosInvoice> records=consumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, PosInvoice> record: records) {
				if(record.value().getDeliveryType().equals("HOME-DELIVERY") && 
						record.value().getDeliveryAddress().getContactNumber().equals("")) {
					producer.send(new ProducerRecord<String, PosInvoice>(AppConfigs.invalidTopicName, record.value().getStoreID(), record.value()));
					logger.info("invalid");
				}
				else {
					producer.send(new ProducerRecord<String, PosInvoice>(AppConfigs.validTopicName, record.value().getStoreID(), record.value()));
					logger.info("valid");
				}
			}
		}
	}
}

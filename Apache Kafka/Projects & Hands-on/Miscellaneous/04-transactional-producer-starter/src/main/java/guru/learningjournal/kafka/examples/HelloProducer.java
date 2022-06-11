package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class HelloProducer {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("Creating Kafka Producer...");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, AppConfigs.transaction);
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        producer.initTransactions();

        logger.info("Start t1 messages...");
        producer.beginTransaction();
        try {
        for (int i = 1; i <= AppConfigs.numEvents; i++) {
            producer.send(new ProducerRecord<>(AppConfigs.topicName, i, "Simple Message-t1" + i));
            producer.send(new ProducerRecord<>(AppConfigs.topicName1, i, "Simple Message-t1" + i));
        }
        producer.commitTransaction();
        }
        catch(Exception e) {
        	producer.abortTransaction();
        	producer.close();
        	throw new RuntimeException();
        }
        
        logger.info("Start t2 messages...");
        producer.beginTransaction();
        try {
        for (int i = 1; i <= AppConfigs.numEvents; i++) {
            producer.send(new ProducerRecord<>(AppConfigs.topicName, i, "Simple Message-t2" + i));
            producer.send(new ProducerRecord<>(AppConfigs.topicName1, i, "Simple Message-t2" + i));
        }
        producer.abortTransaction();
        }
        catch(Exception e) {
        	producer.abortTransaction();
        	producer.close();
        	throw new RuntimeException();
        }
        
        

    }
}

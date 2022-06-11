package guru.learningjournal.kafka.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DispatcherDemo {
	private static final Logger logger=LogManager.getLogger();
	public static void main(String[] args) {
		Properties properties=new Properties();
		try {
			InputStream inputStream=new FileInputStream(AppConfigs.kakfkaConfigFileLocation);
			properties.load(inputStream);
			properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
			properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
			properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KafkaProducer<Integer, String> kafkaProducer=new KafkaProducer<Integer, String>(properties);
		Thread[] threads=new Thread[AppConfigs.eventFiles.length];
		logger.info("thread start");
		for(int i=0; i<AppConfigs.eventFiles.length; i++) {
			threads[i]=new Thread(new Dispatcher(AppConfigs.topicName, AppConfigs.eventFiles[i], kafkaProducer));
			threads[i].start();
		}
		try {
			for(Thread t:threads)
					t.join();
		}
		catch(Exception e) {
			logger.error("error");
		}
		finally {
			kafkaProducer.close();
			logger.info("done");
		}
	}
}

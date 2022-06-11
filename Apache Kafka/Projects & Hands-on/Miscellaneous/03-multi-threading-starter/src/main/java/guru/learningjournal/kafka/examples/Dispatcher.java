package guru.learningjournal.kafka.examples;


import java.io.File;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.Logger;

public class Dispatcher implements Runnable {
	private static final Logger logger=org.apache.logging.log4j.LogManager.getLogger();
	private String fileLocation;
	private String topicName;
	private KafkaProducer<Integer, String> producer;
	
	
	public Dispatcher(String topicName, String fileLocation, KafkaProducer<Integer, String> producer) {
		super();
		this.topicName = topicName;
		this.fileLocation = fileLocation;
		this.producer = producer;
	}


	@Override
	public void run() {
		logger.info("start"+fileLocation);
		File file=new File(fileLocation);
		try(Scanner scanner=new Scanner(file)){
			while(scanner.hasNextLine()) {
				String line=scanner.nextLine();
				producer.send(new ProducerRecord<Integer, String>(topicName, null, line));
				}
			logger.info("done"+fileLocation);
		}
		catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	
}

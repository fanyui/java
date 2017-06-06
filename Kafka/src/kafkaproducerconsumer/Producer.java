package kafkaproducerconsumer;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {
	private static String topicName;
	private static Properties props = new Properties();
	public static void main(String [] args){
		//configuring the kafka producer
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", new Integer(1));
		props.put("batch.size",new Integer(0));
		props.put("linger.ms", new Integer(1));
		props.put("buffer.memory", new Integer(33554432));
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//done configuring
		//create a topic name 
		 
		//create a producer and two topic then send som messages to the topics
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		 for(int i = 0; i < 100; i++){
		     producer.send(new ProducerRecord<String, String>("Harisu", Integer.toString(i), Integer.toString(i)));
		 	 producer.send(new ProducerRecord<String,String>("Topic2","This is message from topic number two "+Integer.toString(i)) );
		 }
		 producer.close();
		 
		
		producer.close();
		System.out.print("success creating producer ");
		
	}
}

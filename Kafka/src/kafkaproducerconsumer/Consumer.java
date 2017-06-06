package kafkaproducerconsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {
private static String topicName;
private	static	Properties props = new Properties();
public static void main(String[] args){ 
	 
	//configuration for the kafka consumer
	props.put("bootstrap.servers", "localhost:9092");
	props.put("group.id", "test");
	props.put("enable.auto.commit", "false");
	props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	
	//create a kafka consumer 
	KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
	
	//subscribe  consumer to a particular topic form multiple topics pass them in the array.aslist
	consumer.subscribe(Arrays.asList("Harisu"));
	 //System.out.print("kfk started and subscription done on topic Harisu");
	List<ConsumerRecord<String,String>> buffer = new ArrayList<>();
	while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records)
            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
    }
	
	//consumer.close();
}

}

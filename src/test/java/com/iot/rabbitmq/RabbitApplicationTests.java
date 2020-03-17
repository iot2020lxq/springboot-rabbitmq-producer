package com.iot.rabbitmq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iot.rabbitmq.entity.Order;
import com.iot.rabbitmq.producer.TopicProducer2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitApplicationTests {

	@Autowired
	private TopicProducer2 topicProducer2;
	
	private static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	public void testSend() throws Exception{
		Map<String,Object> properties = new HashMap<>();
		properties.put("number", "19980701");
		properties.put("send_time", dataFormat.format(new Date()));
		topicProducer2.sendEmailMsg("Hello Email!", properties);
	}
	
	@Test
	public void testSendOrder() throws Exception{
		Map<String,Object> properties = new HashMap<>();
		properties.put("number", "19980822");
		properties.put("send_time", dataFormat.format(new Date()));
		Order order = new Order();
		order.setId("2");
		topicProducer2.sendEmailMsgBindOrder(order);
	}
	
}

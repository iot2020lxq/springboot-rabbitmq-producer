package com.iot.rabbitmq.producer;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class TopicProducer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	//发送邮件消息
	public void sendEmailMsg() throws IOException, TimeoutException {
		
		String msg = "msg：" + new Date();
		//发送消息
		amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "topicEmail.#", msg);
	}
	
	//发送短信消息
	public void sendSmslMsg() throws IOException, TimeoutException {
		String msg = "msg：" + new Date();
		//发送消息
		amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "topicSms.#", msg);
	}
	
}

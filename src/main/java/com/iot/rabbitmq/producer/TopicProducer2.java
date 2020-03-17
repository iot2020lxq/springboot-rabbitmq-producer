package com.iot.rabbitmq.producer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.iot.rabbitmq.entity.Order;


@Component
public class TopicProducer2 {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/* 
	 * 1.确认消息模式
	 */
	final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
		
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			System.err.println("CorrelationData："+correlationData);
			System.err.println("ack："+ack);
			if(!ack) {
				System.err.println("异常处理......");
			}
		}
	};
	
	/*
	 * 2.返回消息模式
	 */
	final ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback(){

		@Override
		public void returnedMessage(org.springframework.amqp.core.Message message,
				int replyCode, String replyText, String exchange,String routingKey) {
			System.err.println("replyCode："+replyCode+"，replyText："+replyText+"，exchange："+exchange+"，routingKey："+routingKey);
		}
	};
	
	
	//发送邮件消息
	public void sendEmailMsg(Object message,Map<String,Object> properties) throws IOException, TimeoutException {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message<Object> msg = MessageBuilder.createMessage(message, mhs);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		//id+时间戳，唯一id
		CorrelationData correlationData = new CorrelationData("email_123");
		//发送消息
		rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "topicEmail.iot", msg,correlationData);
	}
	
	//发送短信消息
	public void sendSmslMsg(Object message,Map<String,Object> properties) throws IOException, TimeoutException {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message<Object> msg = MessageBuilder.createMessage(message, mhs);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		//id+时间戳，唯一id
		CorrelationData correlationData = new CorrelationData("sms_123");
		//发送消息
		rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "topicSms.iot", msg,correlationData);
	}
	

	//发送邮件消息2，绑定一个对象
	public void sendEmailMsgBindOrder(Order order) throws IOException, TimeoutException {
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		//id+时间戳，唯一id
		CorrelationData correlationData = new CorrelationData("emailOrder_123");
		//发送消息
		rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "topicEmailOrder.iot", order,correlationData);
	}
}

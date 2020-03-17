package com.iot.rabbitmq.producer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rabbitmq.client.Channel;


@Component
public class FanoutProducer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	//@Autowired
	//private Channel channel;
	
	public void sendMsg(/*String queueName*/) throws IOException, TimeoutException {
		//开启消息事物
		try {
			//channel.txSelect();
			
			String msg = "msg：" + new Date();
			//发送消息
			amqpTemplate.convertAndSend("FANOUT_EXCHANGE","",msg);
			System.out.println(msg);
			//int i = 1/0;
			//channel.txCommit();
		} catch (Exception e) {
			e.printStackTrace();
			//channel.txRollback();
		}finally {
			//channel.close();
		}
		
	}
}

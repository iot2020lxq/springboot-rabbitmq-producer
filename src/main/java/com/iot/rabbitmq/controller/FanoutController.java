package com.iot.rabbitmq.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.rabbitmq.producer.FanoutProducer;

@RestController
public class FanoutController {

	@Autowired
	private FanoutProducer fanoutProducer;
	
	@RequestMapping("/fanout")
	public String fanoutSendMsg(String queueName) {
		try {
			fanoutProducer.sendMsg(/*queueName*/);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return "SUCCESS:"+queueName;
	}
}

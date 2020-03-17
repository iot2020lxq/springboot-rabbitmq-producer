package com.iot.rabbitmq.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.rabbitmq.producer.TopicProducer;

@RestController
public class TopicController {

	@Autowired
	private TopicProducer topicProducer;
	
	@RequestMapping("/topicEmail")
	public String sendEmailMsg() {
		try {
			topicProducer.sendEmailMsg();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return "SUCCESS:";
	}
	
	@RequestMapping("/topicSms")
	public String sendSmsMsg() {
		try {
			topicProducer.sendSmslMsg();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return "SUCCESS:";
	}
}

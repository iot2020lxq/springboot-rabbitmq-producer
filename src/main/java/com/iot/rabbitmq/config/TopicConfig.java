package com.iot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布订阅模式配置交换机为fanout模式
 * @author Administrator
 *
 */
@Configuration
public class TopicConfig {
	
	//交换机名称
	private static final String TOPICCHANGE_NAME = "TOPIC_EXCHANGE";
	//邮件队列名称
	private static final String EMAIL_QUEUE_NAME_TOPIC = "emailQueue_topic";
	//短信队列名称
	private static final String SMS_QUEUE_NAME_TOPIC = "smsQueue_topic";

	//1.定义交换机
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPICCHANGE_NAME,true,false);
	}
	
	//2.定义邮件队列
	@Bean
	public Queue emailQueue_topic() {
		return new Queue(EMAIL_QUEUE_NAME_TOPIC,true);
	}
	
	//3.定义邮件队列
	@Bean
	public Queue smsQueue_topic() {
		return new Queue(SMS_QUEUE_NAME_TOPIC,true);
	}
	
	//4.邮箱队列队列和交换机绑定，参数名称要和定义的队列、交换机方法名一致
	@Bean
	public Binding bindingExchangeEmail_topic(/*Queue emailQueue_topic,TopicExchange topicExchange*/) {
		return BindingBuilder.bind(emailQueue_topic()).to(topicExchange()).with("topicEmail.#");
	}
	
	//4.短信队列和交换机绑定，参数名称要和定义的队列、交换机方法名一致
	@Bean
	public Binding bindingExchangeSms_topic(/*Queue smsQueue_topic,TopicExchange topicExchange*/) {
		return BindingBuilder.bind(smsQueue_topic()).to(topicExchange()).with("topicSms.#");
	}
}

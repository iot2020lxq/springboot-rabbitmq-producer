package com.iot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Channel;

/**
 * 发布订阅模式配置交换机为fanout模式
 * @author Administrator
 *
 */
@Configuration
public class FanoutConfig {
	
	//交换机名称
	private static final String FANOUTCHANGE_NAME = "FANOUT_EXCHANGE";
	//邮件队列名称
	private static final String EMAIL_QUEUE_NAME = "email_queue";
	//短信队列名称
	private static final String SMS_QUEUE_NAME = "sms_queue";

	//1.定义交换机
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUTCHANGE_NAME);
	}
	
	//2.定义邮件队列
	@Bean
	public Queue email_queue() {
		return new Queue(EMAIL_QUEUE_NAME);
	}
	
	//3.定义邮件队列
	@Bean
	public Queue sms_queue() {
		return new Queue(SMS_QUEUE_NAME);
	}
	
	//4.邮箱队列队列和交换机绑定，参数名称要和定义的队列、交换机方法名一致
	@Bean
	public Binding bindingExchangeEmail(Queue email_queue,FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(email_queue).to(fanoutExchange);
	}
	
	//4.短信队列和交换机绑定，参数名称要和定义的队列、交换机方法名一致
	@Bean
	public Binding bindingExchangeSms(Queue sms_queue,FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(sms_queue).to(fanoutExchange);
	}
	
}

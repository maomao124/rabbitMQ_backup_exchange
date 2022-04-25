package mao.rabbitmq_backup_exchange.controller;

import mao.rabbitmq_backup_exchange.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Project name(项目名称)：rabbitMQ_backup_exchange
 * Package(包名): mao.rabbitmq_backup_exchange.controller
 * Class(类名): MyController
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 14:05
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@RestController
public class MyController
{
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/test1/{message}")
    public String test1(@PathVariable String message)
    {
        log.info("开始发送消息：" + message);
        UUID uuid = UUID.randomUUID();
        rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE_NAME,
                "key2", message, new MessagePostProcessor()
                {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException
                    {
                        message.getMessageProperties().setMessageId(uuid.toString());
                        return message;
                    }
                });
        return message;
    }
}

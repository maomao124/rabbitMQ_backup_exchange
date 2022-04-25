package mao.rabbitmq_backup_exchange.consumer;

import mao.rabbitmq_backup_exchange.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * Project name(项目名称)：rabbitMQ_backup_exchange
 * Package(包名): mao.rabbitmq_backup_exchange.consumer
 * Class(类名): Consumer3
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 14:22
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Component
public class Consumer3
{
    private static final Logger log = LoggerFactory.getLogger(Consumer1.class);

    @RabbitListener(queues = {RabbitMQConfig.WARNING_QUEUE_NAME})
    public void getMessage(String message, Message m)
    {
        log.warn("发现不可路由信息：" + message + ",uuid：" + m.getMessageProperties().getMessageId());
    }
}

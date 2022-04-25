package mao.rabbitmq_backup_exchange.consumer;

import mao.rabbitmq_backup_exchange.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Project name(项目名称)：rabbitMQ_backup_exchange
 * Package(包名): mao.rabbitmq_backup_exchange.consumer
 * Class(类名): Consumer2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 14:21
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Component
public class Consumer2
{
    private static final Logger log = LoggerFactory.getLogger(Consumer1.class);

    @RabbitListener(queues = {RabbitMQConfig.BACKUP_QUEUE_NAME})
    public void getMessage(String message)
    {
        log.info("消费者2(备份)接收到消息：" + message);
    }
}

package mao.rabbitmq_backup_exchange.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Project name(项目名称)：rabbitMQ_backup_exchange
 * Package(包名): mao.rabbitmq_backup_exchange.config
 * Class(类名): RabbitMQConfig
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 14:04
 * Version(版本): 1.0
 * Description(描述)： 无
 */


@Configuration
public class RabbitMQConfig
{
    /**
     * The constant CONFIRM_EXCHANGE_NAME.
     */
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    /**
     * The constant CONFIRM_QUEUE_NAME.
     */
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";

    /**
     * The constant BACKUP_EXCHANGE_NAME.
     */
    public static final String BACKUP_EXCHANGE_NAME = "backup_exchange";
    /**
     * The constant BACKUP_QUEUE_NAME.
     */
    public static final String BACKUP_QUEUE_NAME = "backup_queue";
    /**
     * The constant WARNING_QUEUE_NAME.
     */
    public static final String WARNING_QUEUE_NAME = "warning_queue";

    /**
     * Gets message converter.
     *
     * @return the message converter
     */
    @Bean
    public MessageConverter getMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Backup exchange fanout exchange.
     *
     * @return the fanout exchange
     */
    @Bean
    public FanoutExchange backup_exchange()
    {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME, false, false, null);
    }

    /**
     * Backup queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue backup_queue()
    {
        return new Queue(BACKUP_QUEUE_NAME, false, false, false, null);
    }

    /**
     * Warning queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue warning_queue()
    {
        return new Queue(WARNING_QUEUE_NAME, false, false, false, null);
    }

    /**
     * Backup exchange bind backup queue binding.
     *
     * @param backup_exchange the backup exchange
     * @param backup_queue    the backup queue
     * @return the binding
     */
    @Bean
    Binding backup_exchange_bind_backup_queue(@Qualifier("backup_exchange") FanoutExchange backup_exchange,
                                              @Qualifier("backup_queue") Queue backup_queue)
    {
        return BindingBuilder.bind(backup_queue).to(backup_exchange);
    }

    /**
     * Backup exchange bind warning queue binding.
     *
     * @param backup_exchange the backup exchange
     * @param warning_queue   the warning queue
     * @return the binding
     */
    @Bean
    Binding backup_exchange_bind_warning_queue(@Qualifier("backup_exchange") FanoutExchange backup_exchange,
                                               @Qualifier("warning_queue") Queue warning_queue)
    {
        return BindingBuilder.bind(warning_queue).to(backup_exchange);
    }

    /**
     * Confirm exchange direct exchange.
     *
     * @return the direct exchange
     */
    @Bean
    public DirectExchange confirm_exchange()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange", BACKUP_EXCHANGE_NAME);
        return new DirectExchange(CONFIRM_EXCHANGE_NAME, false, false, map);
    }

    /**
     * Confirm queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue confirm_queue()
    {
        return new Queue(CONFIRM_QUEUE_NAME, false, false, false, null);
    }

    /**
     * Confirm exchange bind confirm queue binding.
     *
     * @param confirm_exchange the confirm exchange
     * @param confirm_queue    the confirm queue
     * @return the binding
     */
    @Bean
    public Binding confirm_exchange_bind_confirm_queue(@Qualifier("confirm_exchange") DirectExchange confirm_exchange,
                                                       @Qualifier("confirm_queue") Queue confirm_queue)
    {
        return BindingBuilder.bind(confirm_queue).to(confirm_exchange).with("key1");
    }
}

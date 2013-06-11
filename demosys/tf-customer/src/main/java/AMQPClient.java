/**
 * User: luke
 */

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AMQPClient {

    private transient final String host;
    private transient final Integer port;
    private transient final String user;
    private transient final String pass;
    private transient final String vHost;
    private transient final int delayBetweenMessages;

    private transient Connection connection;

    private transient Channel channel;

    public AMQPClient(final String host, final Integer port, final String user, final String pass, final String vHost, final int delayBetweenMessages) {

        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.vHost = vHost;
        this.delayBetweenMessages = delayBetweenMessages;
    }

    /**
     * Publisher a list of messages based on input data.
     *
     * @param inputMessageList list of input data.
     */

    public void sendMessages(final List<InputMessage> inputMessageList) {

        this.openConnection();

        for (final InputMessage inputMessage : inputMessageList) {
            this.sendMessage(inputMessage);
            try {
                Thread.sleep(this.delayBetweenMessages);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.closeConnection();
    }

    public void sendMessage(final InputMessage inputMessage) {

        final String exchangeName = inputMessage.getExchangeName();
        final String routingKey = inputMessage.getRoutingKey();
        final Map<String, Object> headers = inputMessage.getHeaders();
        final String body = inputMessage.getPayloadAsString();

        this.sendMessage(exchangeName, routingKey, headers, body);
    }

    private void sendMessage(final String exchangeName, final String routingKey, final Map<String, Object> headers, final String body) {
        openConnection();

        final AMQP.BasicProperties.Builder propertiesBuilder = new AMQP.BasicProperties.Builder();
        propertiesBuilder.headers(headers);
        propertiesBuilder.contentType("text/x-json");

        final AMQP.BasicProperties basicProperties = propertiesBuilder.build();

        byte[] payload = null;
        if (body != null) {
            payload = body.getBytes();
        }
        try {
            this.channel.basicPublish(exchangeName, routingKey, true, false, basicProperties, body.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        closeConnection();
    }

    private void openConnection() {

        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);
        factory.setPort(this.port);
        factory.setUsername(this.user);
        factory.setPassword(this.pass);
//        factory.setVirtualHost(this.vHost);

        try {
            this.connection = factory.newConnection();
            this.channel = this.connection.createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    private void closeConnection() {

        try {
            this.channel.close();
            this.connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * User: luke
 */
public class Client {

    private static AMQPClient amqpClient = new AMQPClient("localhost", 5672, "guest", "guest", "", 0);

    public static void main(String[] args) {

        amqpClient.sendMessage(new InputMessage("exchange.in", "message.billing.customer", "{\"message\" : \"OK\"}"));
    }
}

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * User: luke
 */
public class InputMessage {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String exchangeName;
    private String routingKey;
    private String payloadAsString;
    private Map<String, Object> headers;

    public InputMessage(String exchangeName, String routingKey, String payloadAsString) {
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.payloadAsString = payloadAsString;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public String getPayloadAsString() {
        try {
            return OBJECT_MAPPER.writeValueAsString(new CustomerMessage("OK"));
        } catch (IOException e) {
            return null;
        }
    }
}

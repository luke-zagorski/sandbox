package pl.com.zagorski.msg.data.enums;

/**
 * User: luke
 */
public enum ContactStatusEnum {

    BLOCKED("blocked"),
    OFFLINE("offline"),
    ONLINE("online"),
    INCOMING_CHAT("incoming"),
    NONE("none");

    private String name;

    ContactStatusEnum(String name) {
        this.name = name;
    }


}

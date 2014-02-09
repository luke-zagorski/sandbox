package pl.com.zagorski.msg.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * User: luke
 */
@Entity
@Cacheable
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String nickname;
    private Integer status;
    private String statusMessage;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;


    @OneToMany(mappedBy = "toUser", fetch = FetchType.LAZY)
    private Set<Message> incomingMessages;
    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private Set<Message> outgoingMessages;

    @OneToMany(mappedBy = "ownUser", fetch = FetchType.LAZY)
    private Set<Contact> haveContacts;
    @OneToMany(mappedBy = "isUser", fetch = FetchType.LAZY)
    private Set<Contact> inContacts;


    /**
     * OUTGOING MESSAGES
     */

    public Set<Message> getOutgoingMessages() {
        return outgoingMessages;
    }

    public void setOutgoingMessages(Set<Message> outgoingMessage) {
        this.outgoingMessages = outgoingMessage;
    }

    void internalAddOutgoingMessage(Message outgoingMessage) {
        outgoingMessages.add(outgoingMessage);
    }

    void internalRemoveOutgoingMessage(Message outgoingMessage) {
        outgoingMessages.remove(outgoingMessage);
    }

    public void addOutgoingMessage(Message outgoingMessage) {
        outgoingMessage.setFromUser(this);
    }

    /**
     * INCOMING MESSAGES
     */

    public Set<Message> getIncomingMessages() {
        return incomingMessages;
    }

    public void setIncomingMessages(Set<Message> incomingMessage) {
        this.incomingMessages = incomingMessage;
    }

    void internalAddIncomingMessage(Message incomingMessage) {
        incomingMessages.add(incomingMessage);
    }

    void internalRemoveIncomingMessage(Message incomingMessage) {
        incomingMessages.remove(incomingMessage);
    }

    public void addIncomingMessage(Message incomingMessage) {
        incomingMessage.setToUser(this);
    }

    /**
     * HAVE CONTACTS
     */
    public Set<Contact> getHaveContacts() {
        return haveContacts;
    }

    public void setHaveContacts(Set<Contact> haveContacts) {
        this.haveContacts = haveContacts;
    }

    void internalAddToContacts(Contact contact) {
        haveContacts.add(contact);
    }

    void internalRemoveFromContacts(Contact contact) {
        haveContacts.remove(contact);
    }

    public void addToContacts(Contact contact) {
        contact.setOwnUser(this);
    }

    /**
     * IN CONTACTS
     */

    public Set<Contact> getInContacts() {
        return inContacts;
    }

    public void setInContacts(Set<Contact> inContacts) {
        this.inContacts = inContacts;
    }

    void internalAddToInContacts(Contact contact) {
        inContacts.add(contact);
    }

    void internalRemoveFromInContacts(Contact contact) {
        inContacts.remove(contact);
    }

    public void addToInContacts(Contact contact) {
        contact.setIsUser(this);
    }


    @Column(unique = true)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("User");
        sb.append("{role='").append(role).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", statusMessage='").append(statusMessage).append('\'');
        sb.append(", status=").append(status);
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package pl.com.zagorski.msg.data;

/**
 * Date: 01.03.12
 */
public interface ContactManager {

    void blockUser(String nickname, String withUser);

    void unblockUser(String nickname, String withUser);
}

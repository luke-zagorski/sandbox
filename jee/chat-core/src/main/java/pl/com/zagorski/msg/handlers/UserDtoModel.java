package pl.com.zagorski.msg.handlers;

import org.primefaces.model.SelectableDataModel;
import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.UserManager;

import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import java.util.List;

/**
 * User: luke
 */
public class UserDtoModel extends ListDataModel<UserBean> implements SelectableDataModel<UserBean> {

    @Inject
    private UserManager userManager;



    public UserDtoModel() {
    }

    public UserDtoModel(List<UserBean> userBeanList) {
        super(userBeanList);
    }

    @Override
    public Object getRowKey(UserBean userBean) {
        return userBean.getNickname();
    }

    @Override
    public UserBean getRowData(String rowKey) {
        return userManager.retrieveUser(rowKey);
    }


}

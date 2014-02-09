package pl.com.zagorski.msg.data;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.domain.User;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * User: luke
 */
@RunWith(Arquillian.class)
public class UserManagerBasicImplTest extends AbstractTest {


    @Inject
    UserManager userManager;

    @Deployment
    public static Archive<?> createTestArchive() {

        return ShrinkWrap.create(WebArchive.class, "users.war")
                .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
                .addAsResource("datasets/import.sql", "import.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addPackages(true, "pl.com.zagorski.msg.util")
                .addPackages(true, "pl.com.zagorski.msg.data")
                .addPackages(true, "pl.com.zagorski.msg.domain")
                .addPackages(true, "pl.com.zagorski.msg.bean");
    }


    @Test
    public void testRegister() throws Exception {

        //given
        User user = new User();
        user.setRole(DB_USER_ROLE);
        user.setNickname("luke2");
        user.setPassword("qwerty12345");

        //when
        userManager.registerUser(user);

        //then
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test(expected = RuntimeException.class)
    public void testFailRegister() throws Exception {

        //given + import.sql
        User user = new User();
        user.setRole(DB_USER_ROLE);
        user.setNickname(DB_EXISTING_USERNAME);
        user.setPassword("qwerty12345");

        //when
        userManager.registerUser(user);

        //then
        assertNotNull(user);
        assertNotNull(user.getId());
    }


    @Test
    public void testRetrieveUser() throws Exception {

        //given + import.sql
        String username = DB_EXISTING_USERNAME;
        UserBean user = null;

        //when
        user = userManager.retrieveUser(username);

        //then
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getNickname());
    }

    @Test
    public void testNotRetrieveUser() throws Exception {

        //given
        String username = "Lukex";
        UserBean user = null;

        //when
        user = userManager.retrieveUser(username);

        //then
        assertNull(user);
    }

}

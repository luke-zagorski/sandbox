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
import pl.com.zagorski.msg.domain.Contact;

import javax.inject.Inject;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * User: luke
 */
@RunWith(Arquillian.class)
public class ContactsProducerBasicImplTest extends AbstractTest {


    @Inject
    ContactsProducer contactsProducer;

    @Deployment
    public static Archive<?> createTestArchive() {

        return ShrinkWrap.create(WebArchive.class, "users.war")
                .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
                .addAsResource("datasets/import_contacts.sql", "import.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addPackages(true, "pl.com.zagorski.msg.util")
                .addPackages(true, "pl.com.zagorski.msg.data")
                .addPackages(true, "pl.com.zagorski.msg.domain")
                .addPackages(true, "pl.com.zagorski.msg.bean");
    }

    @Test
    public void testGetMyChatContacts() throws Exception {

        //given import.sql

        //when
        List<Contact> contactList = contactsProducer.getMyActionChatContacts(DB_EXISTING_USERNAME);

        //then
        assertNotNull(contactList);
        assertSame(1, contactList.size());
    }
}

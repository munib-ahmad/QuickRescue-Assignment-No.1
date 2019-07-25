package com.qr.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.qr.dao.AccountDao;
import com.qr.dao.ContactDao;
import com.qr.dao.impl.AccountDaoImpl;
import com.qr.dao.impl.ContactDaoImpl;
import com.qr.model.DO.Account;
import com.qr.model.DO.Contact;

public class ContactDaoTest {

	@Test
	public final void testCreate() {
		AccountDao accountDao = new AccountDaoImpl();

		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");
		createUtilAccount(account, accountDao);

		ContactDao contactDao = new ContactDaoImpl();

		Contact contact = new Contact();
		contact = new Contact();
		contact.setAccount(account);
		contact.setFirstName("Jhon");
		contact.setLastName("Doe");
		contact.setEmail("email@example.com");
		contact.setGender(Contact.GENDER_MALE);
		contact.setPhoneNumber("+923367215150");
		contact.setStatus(Contact.STATUS_ACTIVE);
		contact.setStreetAddress("St#2 Hno.33");
		contact.setCity("ISB");
		contact.setState("Punjab");
		contact.setCountry("Pakistan");
		contact.setAccount(account);

		assertNotNull(contactDao.create(contact));
		deleteUtil(account, accountDao);

	}

	@Test
	public final void testUpdateById() {

		AccountDao accountDao = new AccountDaoImpl();

		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");
		createUtilAccount(account, accountDao);

		ContactDao contactDao = new ContactDaoImpl();

		Contact contact = new Contact();
		contact = new Contact();
		contact.setAccount(account);
		contact.setFirstName("Jhon");
		contact.setLastName("Doe");
		contact.setEmail("email@example.com");
		contact.setGender(Contact.GENDER_MALE);
		contact.setPhoneNumber("+923367215150");
		contact.setStatus(Contact.STATUS_ACTIVE);
		contact.setStreetAddress("St#2 Hno.33");
		contact.setCity("ISB");
		contact.setState("Punjab");
		contact.setCountry("Pakistan");
		contact.setAccount(account);

		createUtilContact(contact, contactDao);

		Contact newcontact = new Contact();
		newcontact = new Contact();
		newcontact.setAccount(account);
		newcontact.setFirstName("Jane");
		newcontact.setLastName("Doe");
		newcontact.setEmail("email@example2.com");
		newcontact.setGender(Contact.GENDER_FEMALE);
		newcontact.setPhoneNumber("+923367215150");
		newcontact.setStatus(Contact.STATUS_INACTIVE);
		newcontact.setStreetAddress("St#2 Hno.35");
		newcontact.setCity("ISB");
		newcontact.setState("Punjab");
		newcontact.setCountry("Pakistan");
		newcontact.setAccount(account);

		assertNotNull(contactDao.updateById(contact.getId(), newcontact));

		deleteUtil(account, accountDao);

	}

	@Test
	public final void testDeleteById() {

		AccountDao accountDao = new AccountDaoImpl();

		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");
		createUtilAccount(account, accountDao);

		ContactDao contactDao = new ContactDaoImpl();

		Contact contact = new Contact();
		contact = new Contact();
		contact.setAccount(account);
		contact.setFirstName("Jhon");
		contact.setLastName("Doe");
		contact.setEmail("email@example.com");
		contact.setGender(Contact.GENDER_MALE);
		contact.setPhoneNumber("+923367215150");
		contact.setStatus(Contact.STATUS_ACTIVE);
		contact.setStreetAddress("St#2 Hno.33");
		contact.setCity("ISB");
		contact.setState("Punjab");
		contact.setCountry("Pakistan");
		contact.setAccount(account);

		createUtilContact(contact, contactDao);

		assertNotNull(contactDao.deleteById(contact.getId()));

		deleteUtil(account, accountDao);

	}

	@Test
	public final void testViewAllContactsByAccountId() {
		AccountDao accountDao = new AccountDaoImpl();

		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");
		createUtilAccount(account, accountDao);

		ContactDao contactDao = new ContactDaoImpl();

		Contact contact = new Contact();
		contact = new Contact();
		contact.setAccount(account);
		contact.setFirstName("Jhon");
		contact.setLastName("Doe");
		contact.setEmail("email@example.com");
		contact.setGender(Contact.GENDER_MALE);
		contact.setPhoneNumber("+923367215150");
		contact.setStatus(Contact.STATUS_ACTIVE);
		contact.setStreetAddress("St#2 Hno.33");
		contact.setCity("ISB");
		contact.setState("Punjab");
		contact.setCountry("Pakistan");
		contact.setAccount(account);

		Contact newcontact = new Contact();
		newcontact = new Contact();
		newcontact.setAccount(account);
		newcontact.setFirstName("Jane");
		newcontact.setLastName("Doe");
		newcontact.setEmail("email@example2.com");
		newcontact.setGender(Contact.GENDER_FEMALE);
		newcontact.setPhoneNumber("+923367215150");
		newcontact.setStatus(Contact.STATUS_INACTIVE);
		newcontact.setStreetAddress("St#2 Hno.35");
		newcontact.setCity("ISB");
		newcontact.setState("Punjab");
		newcontact.setCountry("Pakistan");
		newcontact.setAccount(account);

		createUtilContact(contact, contactDao);
		createUtilContact(newcontact, contactDao);

		List<Contact> contactsList = new ArrayList<Contact>(0);
		contactsList.add(contact);
		contactsList.add(newcontact);

		List<Contact> fetchedContacts = contactDao.viewAllContactsByAccountId(account.getId());

		assertEquals(fetchedContacts.size(), contactsList.size());

		for (int index = 0; index < fetchedContacts.size(); index++) {
			assertEquals(contactsList.get(index).getId(), fetchedContacts.get(index).getId());
		}

		deleteUtil(account, accountDao);
	}

	private void deleteUtil(Account account, AccountDao accountDao) {
		accountDao.deleteById(account.getId());
	}

	private void createUtilAccount(Account account, AccountDao accountDao) {
		accountDao.create(account);
	}

	private void createUtilContact(Contact contact, ContactDao contactDao) {
		contactDao.create(contact);
	}

}

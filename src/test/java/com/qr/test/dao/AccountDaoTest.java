package com.qr.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.qr.dao.AccountDao;
import com.qr.dao.impl.AccountDaoImpl;
import com.qr.model.DO.Account;

public class AccountDaoTest {

	@Test
	public final void testCreate() {
		AccountDao accountDao = new AccountDaoImpl();
		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");
		assertNotNull(accountDao.create(account));
		deleteUtil(account, accountDao);

	}

	@Test
	public final void testViewAll() {
		AccountDao accountDao = new AccountDaoImpl();

		Account account1 = new Account();
		account1.setName("Jhon Doe");
		account1.setEmailDomain("example.com");
		account1.setTimeZoneCity("ISB");

		Account account2 = new Account();
		account2.setName("Jhon Doe");
		account2.setEmailDomain("example.com");
		account2.setTimeZoneCity("ISB");

		List<Account> accounts = new ArrayList<Account>(0);

		accounts.add(account1);
		accounts.add(account2);

		createUtil(accounts, accountDao);

		List<Account> fetchedAccounts = accountDao.viewAll();

		assertEquals(accounts.size(), fetchedAccounts.size());

		if (accounts.size() == fetchedAccounts.size()) {
			for (int idx = 0; idx < accounts.size(); idx++) {
				assertEquals(accounts.get(idx).getId(), fetchedAccounts.get(idx).getId());
			}
		}
		deleteUtil(fetchedAccounts, accountDao);
	}

	@Test
	public final void testFindById() {
		AccountDao accountDao = new AccountDaoImpl();

		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");

		createUtil(account, accountDao);

		assertEquals(account.getId(), accountDao.findById(account.getId()).getId());

		deleteUtil(account, accountDao);

	}

	@Test
	public final void testUpdate() {
		AccountDao accountDao = new AccountDaoImpl();

		Account account1 = new Account();
		account1.setName("Jhon Doe");
		account1.setEmailDomain("example.com");
		account1.setTimeZoneCity("ISB");

		createUtil(account1, accountDao);

		Account account2 = new Account();
		account2.setName("Jane Doe");
		account2.setEmailDomain("example2.com");
		account2.setTimeZoneCity("LHR");

		assertNotNull(accountDao.update(account1, account2));

		deleteUtil(account1, accountDao);

	}

	@Test
	public final void testDeleteById() {
		AccountDao accountDao = new AccountDaoImpl();

		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");

		createUtil(account, accountDao);

		assertNotNull(accountDao.deleteById(account.getId()));

	}

	private void deleteUtil(Account account, AccountDao accountDao) {
		accountDao.deleteById(account.getId());
	}

	private void createUtil(Account account, AccountDao accountDao) {
		accountDao.create(account);
	}

	private void createUtil(List<Account> account, AccountDao accountDao) {
		for (Account myAccount : account) {
			accountDao.create(myAccount);
		}
	}

	private void deleteUtil(List<Account> account, AccountDao accountDao) {
		for (Account myAccount : account) {
			accountDao.deleteById(myAccount.getId());
		}
	}

}

package com.qr.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.qr.dao.AccountDao;
import com.qr.dao.ProfileDao;
import com.qr.dao.impl.AccountDaoImpl;
import com.qr.dao.impl.ProfileDaoImpl;
import com.qr.model.DO.Account;
import com.qr.model.DO.Profile;

public class ProfileDaoTest {

	@Test
	public final void testCreate() {
		AccountDao accountDao = new AccountDaoImpl();
		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");
		createUtilAccount(account, accountDao);

		ProfileDao profileDao = new ProfileDaoImpl();
		Profile profile = new Profile();
		profile.setName("Jhon Doe");
		profile.setCity("ISB");
		profile.setCountry("PK");
		profile.setAccount(account);
		profileDao.create(profile);

		assertNotNull(profileDao.create(profile));
		deleteUtil(account, accountDao);
	}

	@Test
	public final void testViewAllProfilessByAccountId() {

		AccountDao accountDao = new AccountDaoImpl();
		Account account = new Account();
		account.setName("Jhon Doe");
		account.setEmailDomain("example.com");
		account.setTimeZoneCity("ISB");
		createUtilAccount(account, accountDao);

		ProfileDao profileDao = new ProfileDaoImpl();
		Profile profile1 = new Profile();
		profile1.setName("Jhon Doe");
		profile1.setCity("ISB");
		profile1.setCountry("PK");
		profile1.setAccount(account);

		Profile profile2 = new Profile();
		profile2.setName("Jhon Doe");
		profile2.setCity("ISB");
		profile2.setCountry("PK");
		profile2.setAccount(account);

		createUtilProfile(profile1, profileDao);
		createUtilProfile(profile2, profileDao);

		List<Profile> profileList = new ArrayList<Profile>(0);
		profileList.add(profile1);
		profileList.add(profile2);

		List<Profile> fetchedProfiles = profileDao.viewAllProfilessByAccountId(account.getId());

		assertEquals(fetchedProfiles.size(), profileList.size());

		for (int index = 0; index < fetchedProfiles.size(); index++) {
			assertEquals(profileList.get(index).getId(), fetchedProfiles.get(index).getId());
		}
		
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

		ProfileDao profileDao = new ProfileDaoImpl();
		Profile profile = new Profile();
		profile.setName("Jhon Doe");
		profile.setCity("ISB");
		profile.setCountry("PK");
		profile.setAccount(account);

		createUtilProfile(profile, profileDao);

		Profile newprofile = new Profile();
		newprofile.setName("Jane Doe");
		newprofile.setCity("LHR");
		newprofile.setCountry("PK");
		newprofile.setAccount(account);

		assertNotNull(profileDao.updateById(profile.getId(), newprofile));

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

		ProfileDao profileDao = new ProfileDaoImpl();
		Profile profile = new Profile();
		profile.setName("Jhon Doe");
		profile.setCity("ISB");
		profile.setCountry("PK");
		profile.setAccount(account);

		createUtilProfile(profile, profileDao);

		assertNotNull(profileDao.deleteById(profile.getId()));

		deleteUtil(account, accountDao);
	}

	private void deleteUtil(Account account, AccountDao accountDao) {
		accountDao.deleteById(account.getId());
	}

	private void createUtilAccount(Account account, AccountDao accountDao) {
		accountDao.create(account);
	}

	private void createUtilProfile(Profile profile, ProfileDao profileDao) {
		profileDao.create(profile);
	}

}

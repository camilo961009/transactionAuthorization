package co.com.innovaschools.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.innovaschools.dao.IAccountDao;
import co.com.innovaschools.model.Account;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao accountDao;

	@Override
	@Transactional
	public Account saveAccount(Account account) {

		List<Account> accounts = listAccount();

		for (int i = 0; i < accounts.size(); i++) {
			if (account.getId() == accounts.get(i).getId()) {
				return null;
			}
		}

		accountDao.save(account);
		return account;
	}

	@Override
	public List<Account> listAccount() {

		return accountDao.findAll();
	}

	@Override
	public Account getAccountByID(int id) {
		// TODO Auto-generated method stub
		return accountDao.getById(id);
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.save(account);
	}

	

}

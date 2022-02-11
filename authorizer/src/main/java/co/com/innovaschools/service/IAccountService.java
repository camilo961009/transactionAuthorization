package co.com.innovaschools.service;

import java.util.List;

import co.com.innovaschools.model.Account;

public interface IAccountService {

	public Account saveAccount(Account account);

	public List<Account> listAccount();

	public Account getAccountByID(int id);

	public Account updateAccount(Account account);

}

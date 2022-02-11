package co.com.innovaschools.service;

import java.util.List;

import co.com.innovaschools.model.Transaction;

public interface ITransactionService {

	public Transaction saveTransaction(Transaction transaction);

	public List<Transaction> listTransactions(int id);

	public Transaction getTransactionByID(int id);

}

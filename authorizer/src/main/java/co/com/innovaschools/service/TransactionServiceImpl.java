package co.com.innovaschools.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.com.innovaschools.dao.ITransactionDao;
import co.com.innovaschools.model.Transaction;

@Service
public class TransactionServiceImpl implements ITransactionService {

	private ITransactionDao transactionDao;

	@Override
	@Transactional
	public Transaction saveTransaction(Transaction transaction) {

		transactionDao.save(transaction);
		return transaction;
	}

	@Override
	public List<Transaction> listTransactions(int id) {
		// TODO Auto-generated method stub
		return transactionDao.findAll();
	}

	@Override
	public Transaction getTransactionByID(int id) {
		// TODO Auto-generated method stub
		return transactionDao.getById(id);
	}

}

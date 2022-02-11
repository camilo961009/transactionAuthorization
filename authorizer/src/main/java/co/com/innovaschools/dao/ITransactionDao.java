package co.com.innovaschools.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.innovaschools.model.Transaction;

public interface ITransactionDao extends JpaRepository<Transaction, Integer> {

}

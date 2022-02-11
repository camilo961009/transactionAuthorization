package co.com.innovaschools.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.innovaschools.model.Account;

public interface IAccountDao extends JpaRepository<Account, Integer> {

}

package co.com.innovaschools.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.innovaschools.model.Account;
import co.com.innovaschools.model.Transaction;
import co.com.innovaschools.service.IAccountService;
import co.com.innovaschools.service.ITransactionService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private ITransactionService transactionService;

	@PostMapping("/account/add")
	public ResponseEntity<?> saveAcount(@Valid @RequestBody Account account, BindingResult result) {
		Account accountNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> err.getDefaultMessage() + " (" + "El campo '" + err.getField() + "')")
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {
			accountNew = accountService.saveAccount(account);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (accountNew == null) {
			response.put("Cuenta", account);
			response.put("violaciones", "Cuenta ya inicializada");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		}
		response.put("Account", accountNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PostMapping("/account/transaction")
	public ResponseEntity<?> transaction(@Valid @RequestBody Transaction transaction, BindingResult result) {
		Account accountNew = null;
		Transaction transactionNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> err.getDefaultMessage() + " (" + "El campo '" + err.getField() + "')")
					.collect(Collectors.toList());

			response.put("violaciones", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		Account account = accountService.getAccountByID(transaction.getId());

		if (account.isActive_cards() == false) {
			response.put("violaciones", "Tarjeta no activa");
			response.put("cuenta", account);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (account.getLimit_available() < transaction.getAmount()) {
			response.put("violaciones", "limite insuficiente");
			response.put("cuenta", account);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		int amount = account.getLimit_available() - transaction.getAmount();

		account.setLimit_available(amount);
		try {
			accountNew = accountService.updateAccount(account);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Account", accountNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}

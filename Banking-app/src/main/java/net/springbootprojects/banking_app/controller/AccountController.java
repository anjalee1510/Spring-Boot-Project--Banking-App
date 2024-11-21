package net.springbootprojects.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springbootprojects.banking_app.dto.AccountDto;
import net.springbootprojects.banking_app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	//API to create an Account
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	// API to get an account by id
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable(value="id") Long id){
		return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
		
	}
	
	//API to deposit amount
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id,@RequestBody Map<String,Double> request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.depositAmount(id, amount);
		return new ResponseEntity<>(accountDto,HttpStatus.OK);
	}
	
	//API to withdraw amount
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id,@RequestBody Map<String,Double> request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdrawAmount(id, amount);
		return new ResponseEntity<>(accountDto,HttpStatus.OK);
	}
	
	//API to get all accounts
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts= accountService.getAllAccounts();
		return new ResponseEntity<>(accounts,HttpStatus.OK);
	}
	
	//API to delete account by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account deleted succefully");
	}
		
	

}

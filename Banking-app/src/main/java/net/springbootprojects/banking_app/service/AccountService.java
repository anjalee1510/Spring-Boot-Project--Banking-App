package net.springbootprojects.banking_app.service;

import java.util.List;

import net.springbootprojects.banking_app.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto depositAmount(Long id, double amount);
	
	
	AccountDto withdrawAmount(Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);
}

package net.springbootprojects.banking_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.springbootprojects.banking_app.dto.AccountDto;
import net.springbootprojects.banking_app.entity.Account;
import net.springbootprojects.banking_app.mapper.AccountMapper;
import net.springbootprojects.banking_app.repository.AccountRepository;
import net.springbootprojects.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		 Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(
				()-> new RuntimeException("Account does not exist.")
				);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto depositAmount(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(
				()-> new RuntimeException("Account does not exist.")
				);
		account.setBalance(account.getBalance()+amount);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdrawAmount(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(
				()-> new RuntimeException("Account does not exist.")
				);
		
		if(amount>account.getBalance()) {
			throw new RuntimeException("Insufficient balance");
		}
		account.setBalance(account.getBalance()-amount);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(
				()-> new RuntimeException("Account does not exist.")
				);
		
		accountRepository.deleteById(id);
	}

}

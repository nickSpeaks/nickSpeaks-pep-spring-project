package com.example.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.dto.*;
import com.example.entity.Account;
import com.example.exception.*;
import com.example.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponse createAccount(CreateAccountRequest request) {
        Optional<Account> account = accountRepository.findAccountByUsername(request.getUsername());
        
        if (account.isPresent()) {
            throw new UserExistsException("Username with this account already exists");
        }
        
        Account newAccount = new Account(request.getUsername(), request.getPassword());
        Account saved = accountRepository.save(newAccount);
        return new AccountResponse(saved.getAccountId(), saved.getUsername(), saved.getPassword());
    }

    public AccountResponse login(LoginAccountRequest loginRequest) {
        Account account = accountRepository.findAccontByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(LoginException::new);
        return new AccountResponse(account.getAccountId(), account.getUsername(), account.getPassword());

    }
}

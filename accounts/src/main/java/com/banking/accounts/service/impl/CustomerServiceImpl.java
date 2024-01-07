package com.banking.accounts.service.impl;

import com.banking.accounts.dto.AccountsDto;
import com.banking.accounts.dto.CardsDto;
import com.banking.accounts.dto.CustomerDetailsDto;
import com.banking.accounts.dto.LoansDto;
import com.banking.accounts.entity.Accounts;
import com.banking.accounts.entity.Customer;
import com.banking.accounts.exception.ResourceNotFoundException;
import com.banking.accounts.mapper.AccountsMapper;
import com.banking.accounts.mapper.CustomerMapper;
import com.banking.accounts.repository.AccountsRepository;
import com.banking.accounts.repository.CustomerRepository;
import com.banking.accounts.service.ICustomerService;
import com.banking.accounts.service.client.CardsFeignClient;
import com.banking.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on given mobile number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        CardsDto cardsDto = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber).getBody();
        LoansDto loansDto = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber).getBody();

        customerDetailsDto.setCardsDto(cardsDto);
        customerDetailsDto.setLoansDto(loansDto);

        return customerDetailsDto;
    }
}

package com.banking.loans.service;

import com.banking.loans.dto.LoansDto;

public interface ILoansService {

    /**
     *
     * @param mobileNumber- Mobile Number of Customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    LoansDto fetchLoan(String mobileNumber);
}

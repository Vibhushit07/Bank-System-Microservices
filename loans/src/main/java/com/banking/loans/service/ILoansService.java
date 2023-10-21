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

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of the Loan details is successful or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if delete of the Loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);
}

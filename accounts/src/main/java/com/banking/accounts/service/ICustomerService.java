package com.banking.accounts.service;

import com.banking.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on given mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}

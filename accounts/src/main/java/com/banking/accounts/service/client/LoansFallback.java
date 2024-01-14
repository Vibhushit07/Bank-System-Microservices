package com.banking.accounts.service.client;

import com.banking.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient {


    /**
     * @param correlationId
     * @param mobileNumber
     * @return
     */
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
}

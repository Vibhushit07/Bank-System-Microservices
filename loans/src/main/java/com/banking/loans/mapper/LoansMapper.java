package com.banking.loans.mapper;

import com.banking.loans.dto.LoansDto;
import com.banking.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoanssDto(Loans loans, LoansDto loansDto) {

        loansDto.setLoanType(loans.getLoanType());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }
}

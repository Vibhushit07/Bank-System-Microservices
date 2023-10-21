package com.banking.loans.service.impl;

import com.banking.loans.constants.LoansConstants;
import com.banking.loans.dto.LoansDto;
import com.banking.loans.entity.Loans;
import com.banking.loans.exception.LoanAlreadyExistException;
import com.banking.loans.exception.ResourceNotFoundException;
import com.banking.loans.mapper.LoansMapper;
import com.banking.loans.repository.LoansRepository;
import com.banking.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile Number of Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        if(loansRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new LoanAlreadyExistException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loansDetail = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber)
        );

        return LoansMapper.mapToLoansDto(loansDetail, new LoansDto());
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of the Loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loansDetail = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loan number", loansDto.getLoanNumber())
        );
        loansRepository.save(LoansMapper.mapToLoans(loansDto, loansDetail));
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if delete of the Loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loansDetail = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber)
        );
        loansRepository.deleteById(loansDetail.getLoanId());
        return true;
    }
}

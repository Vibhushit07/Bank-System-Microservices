package com.banking.loans.controller;


import com.banking.loans.constants.LoansConstants;
import com.banking.loans.dto.LoansDto;
import com.banking.loans.dto.ResponseDto;
import com.banking.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

    private ILoansService iLoansService;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new Loan inside Bank"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCards(@Valid @RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch Loan details based on mobile number"
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoansDetails(@Valid @RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iLoansService.fetchLoan(mobileNumber));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoansDetails(@RequestBody LoansDto loansDto) {
        if(iLoansService.updateLoan(loansDto)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
        if(iLoansService.deleteLoan(mobileNumber)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }
}

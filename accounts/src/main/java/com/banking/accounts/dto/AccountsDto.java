package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of Bank account"
    )
    @NotEmpty(message = "Account Number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be of 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Bank account", example = "Savings"
    )
    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Bank branch address", example = "123 NewYork"
    )
    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}

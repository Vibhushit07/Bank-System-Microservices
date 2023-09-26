package com.banking.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {

    private int customerId;

    @Id
    private long accountNumber;

    private String accountType;

    private String branchAddress;
}

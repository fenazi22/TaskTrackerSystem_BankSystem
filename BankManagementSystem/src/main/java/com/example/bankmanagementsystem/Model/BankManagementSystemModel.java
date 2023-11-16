package com.example.bankmanagementsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankManagementSystemModel {
private String ID , Username ;
private double Balance;
private double Deposit;
private double Withdraw;

}

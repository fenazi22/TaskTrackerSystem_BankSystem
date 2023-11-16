package com.example.bankmanagementsystem.BankController;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.BankManagementSystemModel;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    ArrayList<BankManagementSystemModel> bankModels = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<BankManagementSystemModel> getCustomers() {
        return bankModels;
    }

    @PostMapping("/add")
    public ArrayList<BankManagementSystemModel> addnewCustomers(@RequestBody BankManagementSystemModel banks) {
        bankModels.add(banks);

        return bankModels;
    }

    @PutMapping("/update/{index}")
    public ArrayList<BankManagementSystemModel> updateCustomers(@PathVariable int index, @RequestBody BankManagementSystemModel bankMModel) {
        bankModels.set(index, bankMModel);
        return bankModels;
    }

    @DeleteMapping("/delete/{index}")
    public ArrayList<BankManagementSystemModel> deleteCustomers(@PathVariable int index) {

        bankModels.remove(index);
        return bankModels;
    }


    @PutMapping("/Withdraw/{index}")
    public ApiResponse Withdraws(@PathVariable int index) {
        BankManagementSystemModel withdrawing = bankModels.get(index);
        if (withdrawing.getWithdraw() > withdrawing.getBalance()) {
            return new ApiResponse("You cannot withdraw because  withdraw more than  your balance", "404");
        } else {
            withdrawing.setBalance(withdrawing.getBalance() - withdrawing.getWithdraw());
            bankModels.set(index, withdrawing);
            return new ApiResponse("Seccefully Withdraw", "200");
        }

    }

//    @PutMapping("/deposit/{index}")
//
//    public ApiResponse deposit(@PathVariable int index) {
//        BankManagementSystemModel deposit = bankModels.get(index);
//        deposit.setBalance(deposit.getBalance() + deposit.getDeposit());
//        bankModels.set(index,deposit);
//        return new ApiResponse("Seccefully deposit", "200");
//    }
//


    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index) {
        BankManagementSystemModel deposit = bankModels.get(index);
        for (BankManagementSystemModel search : bankModels) {
            if(search.getDeposit()<search.getBalance())
            search.setBalance(search.getBalance() + search.getDeposit());
            bankModels.set(index, search);
            return new ApiResponse("Successfully deposited", "200");
        }
           return  new ApiResponse("filed ","404");
        }
    }
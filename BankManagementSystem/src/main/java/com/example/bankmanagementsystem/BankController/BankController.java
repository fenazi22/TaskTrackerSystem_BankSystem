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



    @PutMapping("/Withdraw/{index}/{amount}")
    public ApiResponse Withdraws(@PathVariable int index, @PathVariable double amount) {
       if(this.bankModels.get(index).getBalance()>=amount);
        this.bankModels.get(index).setBalance(this.bankModels.get(index).getBalance()-amount);
       return new ApiResponse("withdraw","200");
    }





    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index, @PathVariable double amount) {
        this.bankModels.get(index).setBalance(this.bankModels.get(index).getBalance()+amount);
        return new ApiResponse("deposit","200");
    } 
}

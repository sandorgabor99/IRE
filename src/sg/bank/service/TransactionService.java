package sg.bank.service;


import sg.bank.dto.TransactionDto;
import sg.bank.entity.Account;
import sg.bank.entity.Transactions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class TransactionService {

    private TransactionDto transactionDto = new TransactionDto();

    public TransactionService() {
    }

    public ArrayList<Transactions> getAllTransactions() {
        return transactionDto.getAllTransactions();
    }

    public void createTransaction(Transactions entity){
        transactionDto.createNewTransaction(entity);
    }

    public void updateTransaction(Transactions entity){
        transactionDto.updateTransaction(entity);
    }
}

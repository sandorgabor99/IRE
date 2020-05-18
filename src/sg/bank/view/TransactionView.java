package sg.bank.view;

import sg.bank.entity.Transactions;
import sg.bank.service.TransactionService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("transactionView")
@ViewScoped
public class TransactionView implements Serializable {

    @Inject
    private TransactionService service;

    private Transactions transaction = new Transactions();

    private ArrayList<Transactions> transactions;
    private ArrayList<Transactions> filteredTransactions;


    @PostConstruct
    public void init() {
        System.out.println("transactionService injected : " + service);
        transactions = service.getAllTransactions();

        for(Transactions t : transactions){
            System.out.println(t.toString());
        }
    }


    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public ArrayList<Transactions> getTransactions() {
        if(transactions == null){
            transactions = service.getAllTransactions();
        }
        return transactions;
    }

    public void setTransactions(ArrayList<Transactions> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Transactions> getFilteredTransactions() {
        return filteredTransactions;
    }

    public void setFilteredTransactions(ArrayList<Transactions> filteredTransactions) {
        this.filteredTransactions = filteredTransactions;
    }
}

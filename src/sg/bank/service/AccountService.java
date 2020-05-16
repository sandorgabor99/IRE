package sg.bank.service;

import sg.bank.dto.AccountDto;
import sg.bank.entity.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Random;

@Named
@ApplicationScoped
public class AccountService {
    
    private AccountDto accountDto = new AccountDto();

    public AccountService() {
    }

    public ArrayList<Account> getAllAccounts() {
        return accountDto.getAllAccounts();
    }

    public void createAccount(Account entity) {
        String accountNumber = String.valueOf(entity.getUser().getId()+getRandomNumberString());

        System.out.println("Account id is : " +accountNumber);
        if(accountDto.getAccountByAccountNumber(accountNumber) == null){
            entity.setAccountnumber(accountNumber); //generated id setting
            accountDto.createNewAccount(entity);
        }

    }

    public void updateAccount(Account entity) {
        accountDto.updateAccount(entity);
    }

    public void deleteAccount(Account entity) {
        accountDto.deleteAccount(entity);
    }

    public Account getAccountById(int id) {
        return accountDto.getAccountById(id);
    }

    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }

    public boolean checkIfUserHasNotAccount(int id){
        return accountDto.checkIfUserHasNotAccount(id);
    }
}

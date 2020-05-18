package sg.bank.view;

import sg.bank.entity.Account;
import sg.bank.entity.Transactions;
import sg.bank.entity.Users;
import sg.bank.service.AccountService;
import sg.bank.service.TransactionService;
import sg.bank.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

@Named("accountView")
@ViewScoped
public class AccountView implements Serializable {

    @Inject
    private AccountService service;

    @Inject
    private UserService userService;

    @Inject
    private TransactionService transactionService;

    private ArrayList<Account> accounts;
    private Account account = new Account();
    private Users user = new Users();

    private int money_in;
    private int money_out;

    private Account sender = new Account();
    private Account reciever = new Account();
    private String transfer_note;
    Transactions transaction = new Transactions();

    @PostConstruct
    public void init() {
        System.out.println("accountService injected : " + service);
        accounts = service.getAllAccounts();

    }

    public String addNewAccount() {
        account.setUser(userService.getUserById(user.getId()));
        service.createAccount(account);

        transaction.setBemenoszamla(account.getAccountnumber());
        transaction.setKimenoszamla(account.getAccountnumber());
        transaction.setLeiras("Számla nyitás");
        transaction.setOsszeg(account.getBalance());
        transaction.setDatum(new Timestamp(System.currentTimeMillis()));

        transactionService.createTransaction(transaction);

        transaction = new Transactions();
        account = new Account();
        return "accounts?faces-redirect=true\"";
    }

    public String closeAccount(int accountId) {
        System.out.println("ID is : " + accountId);

        transaction.setBemenoszamla(account.getAccountnumber());
        transaction.setKimenoszamla(account.getAccountnumber());
        transaction.setLeiras("Pénztári kifizetés számlazárás miatt");
        transaction.setOsszeg(account.getBalance());
        transaction.setDatum(new Timestamp(System.currentTimeMillis()));

        transactionService.createTransaction(transaction);

        transaction = new Transactions();

        service.deleteAccount(service.getAccountById(accountId));
        return "accounts?faces-redirect=true\"";
    }

    public void addMoneyToAccount() {
        System.out.println("Id is : " + account.getId());
        account = service.getAccountById(account.getId());
        System.out.println("Account old balance : " + account.getBalance());
        int oldbalance = account.getBalance();
        account.setBalance(account.getBalance() + money_in);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Siker", "Sikeres pénzbefizetés"));

        System.out.println("Account new balance : " + account.getBalance());
        service.updateAccount(account);

        transaction.setBemenoszamla(account.getAccountnumber());
        transaction.setKimenoszamla(account.getAccountnumber());
        transaction.setLeiras("Pénz befizetés");
        transaction.setOsszeg(account.getBalance()-oldbalance);
        transaction.setDatum(new Timestamp(System.currentTimeMillis()));

        transactionService.createTransaction(transaction);

        transaction = new Transactions();

        money_in = 0;

    }

    public void getMoneyFromAccount() {
        FacesContext context = FacesContext.getCurrentInstance();
        account = service.getAccountById(account.getId());
        System.out.println("Account old balance : " + account.getBalance());
        int oldbalance = account.getBalance();
        if (account.getBalance() - money_out > 0) {
            account.setBalance(account.getBalance() - money_out);
            System.out.println("Account new balance : " + account.getBalance());
            service.updateAccount(account);
            context.addMessage(null, new FacesMessage("Siker", "Sikeres pénzkifizetés"));

            transaction.setBemenoszamla(account.getAccountnumber());
            transaction.setKimenoszamla(account.getAccountnumber());
            transaction.setLeiras("Pénz kifizetés");
            transaction.setOsszeg(oldbalance - account.getBalance());
            transaction.setDatum(new Timestamp(System.currentTimeMillis()));

            transactionService.createTransaction(transaction);

            transaction = new Transactions();

        } else if (account.getBalance() - money_out < 0) {
            context.addMessage(null, new FacesMessage("Művelet nem kivitelezhető", "Számlán nincs elég fedezet"));
        }
        money_out = 0;
    }

    public void transferMoney() {
        FacesContext context = FacesContext.getCurrentInstance();
        sender = service.getAccountById(sender.getId());
        reciever = service.getAccountById(reciever.getId());

        System.out.println("Sender account old balance : " + sender.getBalance());
        if(sender.getUser().getId() == reciever.getUser().getId()){
            context.addMessage(null, new FacesMessage("Sikertelen", "A küldő és a fogadó fél nem lehet ugyan az!"));
        }
        else if(money_out<0){
            context.addMessage(null, new FacesMessage("Sikertelen", "Az összegnek pozitív számnak kell lennie!"));
        }
        else if (sender.getBalance() - money_out > 0) {
            sender.setBalance(sender.getBalance() - money_out);
            System.out.println("Sender account new balance : " + sender.getBalance());
            System.out.println("Reciever account old balance : " + reciever.getBalance());
            reciever.setBalance(reciever.getBalance() + money_out);
            System.out.println("Reciever account new balance : " + reciever.getBalance());
            System.out.println("Updating accounts with id1: " + sender.getId() + "id 2 :" + reciever.getId());

            service.updateAccount(sender);
            service.updateAccount(reciever);

            transaction.setBemenoszamla(reciever.getAccountnumber());
            transaction.setKimenoszamla(sender.getAccountnumber());
            transaction.setLeiras(transfer_note);
            transaction.setOsszeg(money_out);
            transaction.setDatum(new Timestamp(System.currentTimeMillis()));

            transactionService.createTransaction(transaction);

            transaction = new Transactions();

            context.addMessage(null, new FacesMessage("Siker", "Sikeres átutalás"));
        } else if (sender.getBalance() - money_out < 0) {
            context.addMessage(null, new FacesMessage("Művelet nem kivitelezhető", "A küldő számlán nincs elég fedezet"));
        }

        money_out = 0;
    }

    public ArrayList<Account> getAccounts() {
        if (accounts == null) {
            accounts = service.getAllAccounts();
        }
        return accounts;
    }

    public ArrayList<Users> getActiveUsersWithoutAccount() {
        ArrayList<Users> allUsers = new ArrayList<>();

        for (Users u : userService.getAllUsers()){
            if(!u.isHasAccount() && u.isActiveUser()){
                allUsers.add(u);
            }
        }
        return allUsers;
    }

    public ArrayList<Account> getActiveAccounts() {
        ArrayList<Account> activeusers = new ArrayList<>();
        for (Account a : accounts) {
            if (a.getUser().isActiveUser() && a.isActiveAccount()) {
                activeusers.add(a);
                System.out.println("\nActive user with active account:" +a.toString());
            }
        }
        return activeusers;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getMoney_in() {
        return money_in;
    }

    public void setMoney_in(int money_in) {
        this.money_in = money_in;
    }

    public int getMoney_out() {
        return money_out;
    }

    public void setMoney_out(int money_out) {
        this.money_out = money_out;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReciever() {
        return reciever;
    }

    public void setReciever(Account reciever) {
        this.reciever = reciever;
    }

    public String getTransfer_note() {
        return transfer_note;
    }

    public void setTransfer_note(String transfer_note) {
        this.transfer_note = transfer_note;
    }
}

package sg.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "account", schema = "sgbank")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "szamlaszam")
    private String accountnumber;

    @Basic
    @Column(name = "egyenleg")
    private int balance;

    @Basic
    @Column(name = "aktiv")
    private boolean activeAccount;

    @OneToOne
    @JoinColumn(name = "userkey")
    private Users user;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(boolean activeAccount) {
        this.activeAccount = activeAccount;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountnumber='" + accountnumber + '\'' +
                ", balance=" + balance +
                ", activeAccount=" + activeAccount +
                ", user=" + user +
                '}';
    }
}

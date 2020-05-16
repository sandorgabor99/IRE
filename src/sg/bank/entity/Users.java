package sg.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "sgbank")
public class Users {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "teljesnev", length = 50)
    private String fullname;

    @Basic
    @Column(name = "telefonszam", length = 20)
    private String mobilenumber;

    @Basic
    @Column(name = "okmany", length = 20)
    private String cardnumber;

    @Basic
    @Column(name = "cim")
    private String address;

    @Basic
    @Column(name = "aktiv")
    private boolean activeUser;

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", address='" + address + '\'' +
                ", activeUser=" + activeUser +
                '}';
    }
}

package sg.bank.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "transaction", schema = "sgbank")
public class Transactions {

    @Id
    @Column(name = "tranID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "bemenoszamla")
    private String bemenoszamla;

    @Basic
    @Column(name = "kimenoszamla")
    private String kimenoszamla;

    @Basic
    @Column(name = "osszeg")
    private int osszeg;

    @Basic
    @Column(name = "datum")
    private Timestamp datum;

    @Basic
    @Column(name = "leiras")
    private String leiras;

    public Transactions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBemenoszamla() {
        return bemenoszamla;
    }

    public void setBemenoszamla(String bemenoszamla) {
        this.bemenoszamla = bemenoszamla;
    }

    public String getKimenoszamla() {
        return kimenoszamla;
    }

    public void setKimenoszamla(String kimenoszamla) {
        this.kimenoszamla = kimenoszamla;
    }

    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", bemenoszamla='" + bemenoszamla + '\'' +
                ", kimenoszamla='" + kimenoszamla + '\'' +
                ", osszeg=" + osszeg +
                ", datum=" + datum +
                ", leiras='" + leiras + '\'' +
                '}';
    }
}

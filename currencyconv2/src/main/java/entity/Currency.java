package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {
    
    @Id
    @Column(name = "isocode")
    private String code;

    @Column(name = "rate")
    private double rate;

    public Currency(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public Currency() {
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}

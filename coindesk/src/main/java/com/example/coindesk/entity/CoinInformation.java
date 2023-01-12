package com.example.coindesk.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coinInfo")
public class CoinInformation implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private int Id;

    @Column(name ="coinName")
    private String coinName;

    @Column(name ="code")
    private String code;

    @Column(name ="rate")
    private String rate;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

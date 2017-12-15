/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inlm4.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ali-PC
 */
@Entity
public class Currency implements CurrencyDTO, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int currencyId;
    private String currency;
    private double rate;

    public Currency(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public Currency() {
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    @Override
    public double convert(double amount, double rateTo) {
        return (amount/rate) * rateTo;
    }
    
}

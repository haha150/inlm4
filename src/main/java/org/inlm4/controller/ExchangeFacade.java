/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inlm4.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.inlm4.integration.ExchangeDAO;
import org.inlm4.model.Currency;
import org.inlm4.model.CurrencyDTO;

/**
 *
 * @author Ali-PC
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ExchangeFacade {
    
    @EJB ExchangeDAO exchangeDB;
    
    public CurrencyDTO createCurrency(String currency, double rate) {
        Currency newCurrency = new Currency(currency,rate);
        exchangeDB.addCurrency(newCurrency);
        return newCurrency;
    }

    public CurrencyDTO findCurrency(String currency) {
        return exchangeDB.findCurrencyFrom(currency);
    }
    
    public List<CurrencyDTO> findCurrencies() {
        return (List) exchangeDB.getCurrencies();
    }
    
    public double convert(String from, String to, double amount) {
        System.out.println(from);
        System.out.println(to);
        CurrencyDTO currencyFrom = findCurrency(from);
        CurrencyDTO currencyTo = findCurrency(to);
        System.out.println(currencyFrom.getCurrency() + currencyFrom.getRate());
        System.out.println(currencyTo.getCurrency() + currencyTo.getRate());
        System.out.println(amount);
        return currencyFrom.convert(amount, currencyTo.getRate());
    }
    
}

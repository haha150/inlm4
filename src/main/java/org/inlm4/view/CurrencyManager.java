/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inlm4.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.inlm4.controller.ExchangeFacade;
import org.inlm4.model.CurrencyDTO;

/**
 *
 * @author Ali-PC
 */
@Named("currencyManager")
@ConversationScoped
public class CurrencyManager implements Serializable {
    @EJB
    private ExchangeFacade exchangeFacade;
    private List<CurrencyDTO> currencies;
    private Double exchangeAmount;
    private String fromCurrency;
    private String toCurrency;
    private Double amount;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;
    
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }

    public Double getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(Double exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public List<CurrencyDTO> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDTO> currencies) {
        this.currencies = currencies;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public void convert() {
        try {
            startConversation();
            transactionFailure = null;
            amount = exchangeFacade.convert(fromCurrency, toCurrency, exchangeAmount);
        } catch (Exception e) {
            handleException(e);
        }
    }
    
    public void onload() {
        try {
            startConversation();
            transactionFailure = null;
            currencies = exchangeFacade.findCurrencies();
        } catch (Exception e) {
            handleException(e);
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inlm4.integration;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.inlm4.model.Currency;

/**
 *
 * @author Ali-PC
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ExchangeDAO {
    
    @PersistenceContext(unitName = "exchangePU")
    private EntityManager em;
    
    public Currency findCurrencyFrom(String currency) {
        Query query = em.createQuery("SELECT c FROM Currency c");
        List<Currency> currencies = query.getResultList();
        if(currencies == null || currencies.isEmpty()) {
            throw new EntityNotFoundException("No currency with name: " + currency);
        }
        for(Currency c : currencies) {
            if(c.getCurrency().equals(currency)) {
                return c;
            }
        }
        throw new EntityNotFoundException("No currency with name: " + currency);
    }
    
    public void addCurrency(Currency currency) {
        em.persist(currency);
    }
    
    public List<Currency> getCurrencies() {
        Query query = em.createQuery("SELECT c FROM Currency c");
        return query.getResultList();
    }
    
}

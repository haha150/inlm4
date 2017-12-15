/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inlm4.model;

/**
 *
 * @author Ali-PC
 */
public interface CurrencyDTO {
    
    public String getCurrency();
    public double getRate();
    public double convert(double amount, double rateTo);
    
}

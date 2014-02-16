/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tables;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author jaspertomas
 */
public class AccountsReceivable {
    String date;
    String customer;
    String invoice;
    String terms;
    String amount;
    String status;

    SimpleDateFormat sdf= new SimpleDateFormat("MMM dd, yyyy");
    public AccountsReceivable(Date date, String customer, String invoice, Integer terms, BigDecimal amount, String status) {
        this.date = sdf.format(date);
        this.customer = customer;
        this.invoice = invoice;
        this.terms = terms.toString();
        this.amount = "0"; 
            if(amount!=null)this.amount=amount.toString();
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }    
    
    public void setDate(Date date) {
        this.date = sdf.format(date);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}

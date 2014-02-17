/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tables;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.Customer;
import models.Terms;

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
    
//    ArrayList<Terms> termslist=null;
    
//    private void initializeTermsList()
//    {
//        if(termslist==null)
//        {
//            termslist=Terms.select("");
//        }
//    }

    SimpleDateFormat sdf= new SimpleDateFormat("MMM dd, yyyy");
    public AccountsReceivable(Date date, Integer customer_id, String invoice, Integer terms_id, BigDecimal amount, String status) {
        //initializeTermsList();
        
        this.date = sdf.format(date);
        this.customer = "";//if(customer_id!=null && customer_id!=0)this.customer=Customer.getById(customer_id).getName();
        this.invoice = invoice;
        this.terms = "";//if(terms_id!=null && terms_id!=0)this.terms=termslist.get(terms_id).getName();
        this.amount = "0";if(amount!=null)this.amount=amount.toString();
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

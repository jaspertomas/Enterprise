package models.query;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountsReceivable {
    //------------FIELDS-----------
    public static final String tablename="accounts_receivable";
    //field names
    public static String[] fields={
            "id"
            ,"date"
            ,"customer"
            ,"invoice"
            ,"terms"
            ,"amount"
            ,"status"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"date"
            ,"varchar(60)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"decimal(10,2)"
            ,"varchar(10)"
            };
    //-----------------------

    public Integer id;
    public Date date;
    public String customer;
    public String invoice;
    public String terms;
    public BigDecimal amount;
    public String status;

    public AccountsReceivable() {
    }
    public AccountsReceivable(ResultSet rs) {
        try {
            id=rs.getInt("invoice.id");
            date=rs.getDate("invoice.date");
            customer=rs.getString("customer.name");
            invoice=rs.getString("invoice.invno");
            terms=rs.getString("terms.name");
            amount=rs.getBigDecimal("invoice.total");
            status=rs.getString("invoice.status");
        } catch (SQLException ex) {
            Logger.getLogger(AccountsReceivable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//	public String getUuid()
//	{
//		return id.toString()+"-";
//	}

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public Date getDate() {
            return date;
    }

    public void setDate(Date date) {
            this.date = date;
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

    public BigDecimal getAmount() {
            return amount;
    }

    public void setAmount(BigDecimal amount) {
            this.amount = amount;
    }

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }


    public String toString()
    {
            return id.toString();
    }

}

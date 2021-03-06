package models.query;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JsonHelper;

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
    public String amountstring;
    public String status;
    public Integer termsdays;
    public Boolean due=false;
    public Boolean overdue=false;
    public BigDecimal amount;
    
    private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar cal = Calendar.getInstance();

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
            termsdays=rs.getInt("terms.days");
            try{
                amountstring=formatter.format(amount).replace("$", "");
            }catch(java.lang.IllegalArgumentException e){
                amountstring="null";
            }
            if(termsdays!=0)
            {
                java.util.Date today=new java.util.Date();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, termsdays);
                java.util.Date duedate = cal.getTime();
                if(duedate.equals(today))due=true;
                else if(duedate.before(today))overdue=true;
            }

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

    public Integer getTermsdays() {
        return termsdays;
    }

    public void setTermsdays(Integer termsdays) {
        this.termsdays = termsdays;
    }

    public Boolean getDue() {
        return due;
    }

    public void setDue(Boolean due) {
        this.due = due;
    }

    public Boolean getOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    public String getAmountstring() {
        return amountstring;
    }

    public void setAmountstring(String amountstring) {
        this.amountstring = amountstring;
    }


    public String toString()
    {
            return id.toString();
    }

    public static class RecordList extends ArrayList<AccountsReceivable>{
        public static RecordList fromJsonString(String resultstring) throws IOException
        {
            return JsonHelper.mapper.readValue(resultstring, RecordList.class);
        }
        public String toEscapedJsonString() throws IOException
        {
            return "\""+JsonHelper.mapper.writeValueAsString(this).replace("\"", "\\\"") +"\"";
        }
    }
}

package models.query;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JsonHelper;
import utils.MySqlDBHelper;

public class AccountsPayable {
    //------------FIELDS-----------
//    public static final String tablename="accounts_payable";
    //field names
    public static String[] fields={
            "id"
            ,"date"
            ,"vendor"
            ,"purchase"
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
    public String vendor;
    public String purchase;
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

    public AccountsPayable() {
    }
    public AccountsPayable(ResultSet rs) {
        try {
            id=rs.getInt("purchase.id");
            date=rs.getDate("purchase.date");
            vendor=rs.getString("vendor.name");
            purchase=rs.getString("purchase.invno");
            terms=rs.getString("terms.name");
            amount=rs.getBigDecimal("purchase.total");
            status=rs.getString("purchase.status");
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
            Logger.getLogger(AccountsPayable.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getVendor() {
            return vendor;
    }

    public void setVendor(String vendor) {
            this.vendor = vendor;
    }

    public String getPurchase() {
            return purchase;
    }

    public void setPurchase(String purchase) {
            this.purchase = purchase;
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


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(date.toString());
            values.add(vendor);
            values.add(purchase);
            values.add(terms);
            values.add(amount.toString());
            values.add(status);

            return values;
    }
//    public void delete()
//    {
//            AccountsPayable.delete(this);
//    }
//    public void save()
//    {
//            if(id==null || id==0)
//                    AccountsPayable.insert(this);
//            else
//                    AccountsPayable.update(this);
//    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static AccountsPayable getByName(String name)
    {
            HashMap<Integer,AccountsPayable> map=select(" name = '"+name+"'");
            for(AccountsPayable item:map)return item;
            return null;
    }	
    */
    public static AccountsPayable getById(Integer id) {
            ArrayList<AccountsPayable> map=select(" id = '"+id.toString()+"'");
            for(AccountsPayable item:map)return item;
            return null;
    }
    //-----------database functions--------------

//    public static void delete(Integer id)
//    {
//        Connection conn=MySqlDBHelper.getInstance().getConnection();            
//        Statement st = null;
//        try { 
//            st = conn.createStatement();
//            st.executeUpdate("delete from "+tablename+" where id = '"+id.toString()+"';");
//        } catch (SQLException ex) {
//            Logger.getLogger(AccountsPayable.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//    }
//    public static void delete(AccountsPayable item)
//    {
//        delete(item.getId());
//    }
//    public static void insert(AccountsPayable item)
//    {
//        Connection conn=MySqlDBHelper.getInstance().getConnection();            
//        Statement st = null;
//        boolean withid=false;
//        try { 
//            st = conn.createStatement();
//            //for tables with integer primary key
//            if(fieldtypes[0].contentEquals("integer"))withid=false;                
//            //for tables with varchar primary key
//            else if(fieldtypes[0].contains("varchar"))withid=true;                
//            st.executeUpdate("INSERT INTO "+tablename+" ("+implodeFields(withid)+")VALUES ("+implodeValues(item, withid)+");");
//        } catch (SQLException ex) {
//            Logger.getLogger(AccountsPayable.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//    }
//    public static void update(AccountsPayable item)
//    {
//        Connection conn=MySqlDBHelper.getInstance().getConnection();            
//        Statement st = null;
//        boolean withid=false;
//        try { 
//            st = conn.createStatement();
//            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
//        } catch (SQLException ex) {
//            Logger.getLogger(AccountsPayable.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//    }
    public static Integer count(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        //if conditions contains a limit clause, remove it. 
        //It is not applicable to a count query
        if(conditions.contains("limit"))
        {
            String[] segments=conditions.split("limit");
            conditions=segments[0];
        }
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
            rs = st.executeQuery("SELECT count(*) from purchase where "+conditions);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsPayable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }

    public static RecordList select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT purchase.id,purchase.date,vendor.name,purchase.invno,terms.name,purchase.total,purchase.status,terms.days,purchase.vendor_id,purchase.terms_id FROM purchase LEFT OUTER JOIN vendor ON purchase.vendor_id=vendor.id LEFT OUTER JOIN terms ON purchase.terms_id=terms.id where "+conditions);

            RecordList items=new RecordList();
            while (rs.next()) {
                items.add(new AccountsPayable(rs));
                    //items.put(rs.getInt("id"), new AccountsPayable(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsPayable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(AccountsPayable item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(withId);
            String output="";
            for(String value:values)
            {
                    if(!output.isEmpty())
                            output+=",";
                    output+="'"+value+"'";
            }
            return output;
    }
    public static String implodeFields(boolean withId)
    {
            String output="";
            for(String field:fields)
            {
                    if(!withId && field.contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=field;
            }
            return output;
    }
    public static String implodeFieldsWithValues(AccountsPayable item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("AccountsPayable:implodeFieldsWithValues(): ERROR: values length does not match fields length");
            }

            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(!withId && fields[i].contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=fields[i]+"='"+values.get(i)+"'";
            }
            return output;
    }	
    public static String implodeFieldsWithTypes()
    {
            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(fields[i].contentEquals(fields[0]))//fields[0] being the primary key
                            output+=fields[i]+" "+fieldtypes[i]+" PRIMARY KEY";
                    else
                            output+=","+fields[i]+" "+fieldtypes[i];
            }
            return output;
    }	
//    public static String createTable()
//    {
//            return "CREATE TABLE IF NOT EXISTS "+tablename+" ("+implodeFieldsWithTypes()+" );";
//    }
//    public static String deleteTable()
//    {
//            return "DROP TABLE IF EXISTS "+tablename;
//    }
    
    public static class RecordList extends ArrayList<AccountsPayable>{
        public static RecordList fromJsonString(String resultstring) throws IOException
        {
            return JsonHelper.mapper.readValue(resultstring, RecordList.class);
        }
        public String toEscapedJsonString() throws IOException
        {
            return "\""+JsonHelper.mapper.writeValueAsString(this).replace("\"", "\\\"") +"\"";
        }
    }

    public static void main(String args[])
    {
        String database="tmcprogram3";
        String url = "jdbc:mysql://localhost:3306/"+database+"?zeroDateTimeBehavior=convertToNull";
        String username="root";
        String password = "password";

        boolean result=MySqlDBHelper.init(url, username, password);            

        //ArrayList<AccountsPayable> items=AccountsPayable.select("");
        RecordList items=AccountsPayable.select("");
        for(AccountsPayable item:items)
        {
            System.out.println(item);
        }
        System.out.println(AccountsPayable.count(""));
    } 
}

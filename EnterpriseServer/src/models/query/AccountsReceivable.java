package models.query;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MySqlDBHelper;

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


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(date.toString());
            values.add(customer);
            values.add(invoice);
            values.add(terms);
            values.add(amount.toString());
            values.add(status);

            return values;
    }
    public void delete()
    {
            AccountsReceivable.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    AccountsReceivable.insert(this);
            else
                    AccountsReceivable.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static AccountsReceivable getByName(String name)
    {
            HashMap<Integer,AccountsReceivable> map=select(" name = '"+name+"'");
            for(AccountsReceivable item:map)return item;
            return null;
    }	
    */
    public static AccountsReceivable getById(Integer id) {
            ArrayList<AccountsReceivable> map=select(" id = '"+id.toString()+"'");
            for(AccountsReceivable item:map)return item;
            return null;
    }
    //-----------database functions--------------

    public static void delete(Integer id)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        try { 
            st = conn.createStatement();
            st.executeUpdate("delete from "+tablename+" where id = '"+id.toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(AccountsReceivable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(AccountsReceivable item)
    {
        delete(item.getId());
    }
    public static void insert(AccountsReceivable item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            //for tables with integer primary key
            if(fieldtypes[0].contentEquals("integer"))withid=false;                
            //for tables with varchar primary key
            else if(fieldtypes[0].contains("varchar"))withid=true;                
            st.executeUpdate("INSERT INTO "+tablename+" ("+implodeFields(withid)+")VALUES ("+implodeValues(item, withid)+");");
        } catch (SQLException ex) {
            Logger.getLogger(AccountsReceivable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(AccountsReceivable item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(AccountsReceivable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static Integer count(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
            Connection conn=MySqlDBHelper.getInstance().getConnection();
            Statement st = null;
            ResultSet rs = null;
            try { 
                st = conn.createStatement();
                rs = st.executeQuery("SELECT count(*) from "+tablename+" where "+conditions);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccountsReceivable.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            return null;
    }

    public static ArrayList<AccountsReceivable> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT invoice.id,invoice.date,customer.name,invoice.invno,terms.name,invoice.total,invoice.status,invoice.customer_id,invoice.terms_id FROM invoice LEFT OUTER JOIN customer ON invoice.customer_id=customer.id LEFT OUTER JOIN terms ON invoice.terms_id=terms.id where "+conditions);

            ArrayList<AccountsReceivable> items=new ArrayList<AccountsReceivable>();
            while (rs.next()) {
                items.add(new AccountsReceivable(rs));
                    //items.put(rs.getInt("id"), new AccountsReceivable(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsReceivable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(AccountsReceivable item,boolean withId)
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
    public static String implodeFieldsWithValues(AccountsReceivable item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("AccountsReceivable:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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
    public static String createTable()
    {
            return "CREATE TABLE IF NOT EXISTS "+tablename+" ("+implodeFieldsWithTypes()+" );";
    }
    public static String deleteTable()
    {
            return "DROP TABLE IF EXISTS "+tablename;
    }
    public static void main(String args[])
    {
        String database="tmcprogram3";
        String url = "jdbc:mysql://localhost:3306/"+database+"?zeroDateTimeBehavior=convertToNull";
        String username="root";
        String password = "password";

        boolean result=MySqlDBHelper.init(url, username, password);            

        ArrayList<AccountsReceivable> items=AccountsReceivable.select("");
        for(AccountsReceivable item:items)
        {
            System.out.println(item);
        }
        System.out.println(AccountsReceivable.count(""));
    } 
}

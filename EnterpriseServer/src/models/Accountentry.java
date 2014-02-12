package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MySqlDBHelper;

public class Accountentry {
    //------------FIELDS-----------
    public static final String tablename="accountentry";
    //field names
    public static String[] fields={
            "id"
            ,"account_id"
            ,"qty"
            ,"date"
            ,"balance"
            ,"ref_class"
            ,"ref_id"
            ,"priority"
            ,"type"
            ,"is_cancelled"
            ,"description"
            ,"created_at"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"decimal(18,2)"
            ,"date"
            ,"decimal(10,2)"
            ,"varchar(20)"
            ,"int(11)"
            ,"int(11)"
            ,"varchar(10)"
            ,"tinyint(4)"
            ,"varchar(100)"
            ,"timestamp"
            };
    //-----------------------

    public Integer id;
    public Integer account_id;
    public BigDecimal qty;
    public Date date;
    public BigDecimal balance;
    public String ref_class;
    public Integer ref_id;
    public Integer priority;
    public String type;
    public Integer is_cancelled;
    public String description;
    public Timestamp created_at;

    public Accountentry() {
    }
    public Accountentry(ResultSet rs) {
        try {
            id=rs.getInt("id");
            account_id=rs.getInt("account_id");
            qty=rs.getBigDecimal("qty");
            date=rs.getDate("date");
            balance=rs.getBigDecimal("balance");
            ref_class=rs.getString("ref_class");
            ref_id=rs.getInt("ref_id");
            priority=rs.getInt("priority");
            type=rs.getString("type");
            is_cancelled=rs.getInt("is_cancelled");
            description=rs.getString("description");
            created_at=rs.getTimestamp("created_at");
        } catch (SQLException ex) {
            Logger.getLogger(Accountentry.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getAccountId() {
            return account_id;
    }

    public void setAccountId(Integer account_id) {
            this.account_id = account_id;
    }

    public BigDecimal getQty() {
            return qty;
    }

    public void setQty(BigDecimal qty) {
            this.qty = qty;
    }

    public Date getDate() {
            return date;
    }

    public void setDate(Date date) {
            this.date = date;
    }

    public BigDecimal getBalance() {
            return balance;
    }

    public void setBalance(BigDecimal balance) {
            this.balance = balance;
    }

    public String getRefClass() {
            return ref_class;
    }

    public void setRefClass(String ref_class) {
            this.ref_class = ref_class;
    }

    public Integer getRefId() {
            return ref_id;
    }

    public void setRefId(Integer ref_id) {
            this.ref_id = ref_id;
    }

    public Integer getPriority() {
            return priority;
    }

    public void setPriority(Integer priority) {
            this.priority = priority;
    }

    public String getType() {
            return type;
    }

    public void setType(String type) {
            this.type = type;
    }

    public Integer getIsCancelled() {
            return is_cancelled;
    }

    public void setIsCancelled(Integer is_cancelled) {
            this.is_cancelled = is_cancelled;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public Timestamp getCreatedAt() {
            return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
            this.created_at = created_at;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(account_id.toString());
            values.add(qty.toString());
            values.add(date.toString());
            values.add(balance.toString());
            values.add(ref_class);
            values.add(ref_id.toString());
            values.add(priority.toString());
            values.add(type);
            values.add(is_cancelled.toString());
            values.add(description);
            values.add(created_at.toString());

            return values;
    }
    public void delete()
    {
            Accountentry.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Accountentry.insert(this);
            else
                    Accountentry.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Accountentry getByName(String name)
    {
            HashMap<Integer,Accountentry> map=select(" name = '"+name+"'");
            for(Accountentry item:map.values())return item;
            return null;
    }	
    */
    public static Accountentry getById(Integer id) {
            HashMap<Integer,Accountentry> map=select(" id = '"+id.toString()+"'");
            for(Accountentry item:map.values())return item;
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
            Logger.getLogger(Accountentry.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Accountentry item)
    {
        delete(item.getId());
    }
    public static void insert(Accountentry item)
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
            Logger.getLogger(Accountentry.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Accountentry item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Accountentry.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static HashMap<Integer, Accountentry> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
            Connection conn=MySqlDBHelper.getInstance().getConnection();
            Statement st = null;
            ResultSet rs = null;
            try { 
                st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

                HashMap<Integer, Accountentry> items=new HashMap<Integer, Accountentry>();
                while (rs.next()) {
                    items.put(rs.getInt("id"), new Accountentry(rs));
                }
                return items;
            } catch (SQLException ex) {
                Logger.getLogger(Accountentry.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return null;
            }

    }
    //-----------database helper functions--------------
    public static String implodeValues(Accountentry item,boolean withId)
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
    public static String implodeFieldsWithValues(Accountentry item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Accountentry:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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
        String url = "jdbc:mysql://localhost:3306/"+database;
        String username="root";
        String password = "password";

        boolean result=MySqlDBHelper.init(url, username, password);            

        HashMap<Integer,Accountentry> items=Accountentry.select("");
        for(Integer key:items.keySet())
        {
            Accountentry item=items.get(key);
            System.out.println(key);
            System.out.println(item.getCreatedAt());
        }
    } 
}

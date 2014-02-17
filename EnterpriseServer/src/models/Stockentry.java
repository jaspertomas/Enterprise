package models;

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

public class Stockentry {
    //------------FIELDS-----------
    public static final String tablename="stockentry";
    //field names
    public static String[] fields={
            "id"
            ,"date"
            ,"qty"
            ,"balance"
            ,"stock_id"
            ,"ref_class"
            ,"ref_id"
            ,"is_cancelled"
            ,"priority"
            ,"type"
            ,"description"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"date"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"int(11)"
            ,"varchar(20)"
            ,"int(11)"
            ,"tinyint(4)"
            ,"int(11)"
            ,"varchar(10)"
            ,"varchar(100)"
            };
    //-----------------------

    public Integer id;
    public Date date;
    public BigDecimal qty;
    public BigDecimal balance;
    public Integer stock_id;
    public String ref_class;
    public Integer ref_id;
    public Integer is_cancelled;
    public Integer priority;
    public String type;
    public String description;

    public Stockentry() {
    }
    public Stockentry(ResultSet rs) {
        try {
            id=rs.getInt("id");
            date=rs.getDate("date");
            qty=rs.getBigDecimal("qty");
            balance=rs.getBigDecimal("balance");
            stock_id=rs.getInt("stock_id");
            ref_class=rs.getString("ref_class");
            ref_id=rs.getInt("ref_id");
            is_cancelled=rs.getInt("is_cancelled");
            priority=rs.getInt("priority");
            type=rs.getString("type");
            description=rs.getString("description");
        } catch (SQLException ex) {
            Logger.getLogger(Stockentry.class.getName()).log(Level.SEVERE, null, ex);
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

    public BigDecimal getQty() {
            return qty;
    }

    public void setQty(BigDecimal qty) {
            this.qty = qty;
    }

    public BigDecimal getBalance() {
            return balance;
    }

    public void setBalance(BigDecimal balance) {
            this.balance = balance;
    }

    public Integer getStockId() {
            return stock_id;
    }

    public void setStockId(Integer stock_id) {
            this.stock_id = stock_id;
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

    public Integer getIsCancelled() {
            return is_cancelled;
    }

    public void setIsCancelled(Integer is_cancelled) {
            this.is_cancelled = is_cancelled;
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

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(date.toString());
            values.add(qty.toString());
            values.add(balance.toString());
            values.add(stock_id.toString());
            values.add(ref_class);
            values.add(ref_id.toString());
            values.add(is_cancelled.toString());
            values.add(priority.toString());
            values.add(type);
            values.add(description);

            return values;
    }
    public void delete()
    {
            Stockentry.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Stockentry.insert(this);
            else
                    Stockentry.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Stockentry getByName(String name)
    {
            HashMap<Integer,Stockentry> map=select(" name = '"+name+"'");
            for(Stockentry item:map)return item;
            return null;
    }	
    */
    public static Stockentry getById(Integer id) {
            ArrayList<Stockentry> map=select(" id = '"+id.toString()+"'");
            for(Stockentry item:map)return item;
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
            Logger.getLogger(Stockentry.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Stockentry item)
    {
        delete(item.getId());
    }
    public static void insert(Stockentry item)
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
            Logger.getLogger(Stockentry.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Stockentry item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Stockentry.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Stockentry.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            return null;
    }

    public static ArrayList<Stockentry> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<Stockentry> items=new ArrayList<Stockentry>();
            while (rs.next()) {
                items.add(new Stockentry(rs));
                    //items.put(rs.getInt("id"), new Stockentry(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(Stockentry.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(Stockentry item,boolean withId)
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
    public static String implodeFieldsWithValues(Stockentry item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Stockentry:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<Stockentry> items=Stockentry.select("");
        for(Stockentry item:items)
        {
            System.out.println(item);
        }
        System.out.println(Stockentry.count(""));
    } 
}

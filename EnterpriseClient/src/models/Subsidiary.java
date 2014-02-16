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

public class Subsidiary {
    //------------FIELDS-----------
    public static final String tablename="subsidiary";
    //field names
    public static String[] fields={
            "id"
            ,"code"
            ,"name"
            ,"account_code"
            ,"fund_id"
            };
    //field types
    public static String[] fieldtypes={
            "bigint(20)"
            ,"varchar(30)"
            ,"varchar(150)"
            ,"varchar(10)"
            ,"bigint(20)"
            };
    //-----------------------

    public Long id;
    public String code;
    public String name;
    public String account_code;
    public Long fund_id;

    public Subsidiary() {
    }
    public Subsidiary(ResultSet rs) {
        try {
            id=rs.getLong("id");
            code=rs.getString("code");
            name=rs.getString("name");
            account_code=rs.getString("account_code");
            fund_id=rs.getLong("fund_id");
        } catch (SQLException ex) {
            Logger.getLogger(Subsidiary.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//	public String getUuid()
//	{
//		return id.toString()+"-";
//	}

    public Long getId() {
            return id;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public String getCode() {
            return code;
    }

    public void setCode(String code) {
            this.code = code;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getAccountCode() {
            return account_code;
    }

    public void setAccountCode(String account_code) {
            this.account_code = account_code;
    }

    public Long getFundId() {
            return fund_id;
    }

    public void setFundId(Long fund_id) {
            this.fund_id = fund_id;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(code);
            values.add(name);
            values.add(account_code);
            values.add(fund_id.toString());

            return values;
    }
    public void delete()
    {
            Subsidiary.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Subsidiary.insert(this);
            else
                    Subsidiary.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Subsidiary getByName(String name)
    {
            HashMap<Long,Subsidiary> map=select(" name = '"+name+"'");
            for(Subsidiary item:map)return item;
            return null;
    }	
    */
    public static Subsidiary getById(Long id) {
            ArrayList<Subsidiary> map=select(" id = '"+id.toString()+"'");
            for(Subsidiary item:map)return item;
            return null;
    }
    //-----------database functions--------------

    public static void delete(Long id)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        try { 
            st = conn.createStatement();
            st.executeUpdate("delete from "+tablename+" where id = '"+id.toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Subsidiary.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Subsidiary item)
    {
        delete(item.getId());
    }
    public static void insert(Subsidiary item)
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
            Logger.getLogger(Subsidiary.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Subsidiary item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Subsidiary.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            return null;
    }

    public static ArrayList<Subsidiary> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<Subsidiary> items=new ArrayList<Subsidiary>();
            while (rs.next()) {
                items.add(new Subsidiary(rs));
                    //items.put(rs.getLong("id"), new Subsidiary(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(Subsidiary.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(Subsidiary item,boolean withId)
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
    public static String implodeFieldsWithValues(Subsidiary item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Subsidiary:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<Subsidiary> items=Subsidiary.select("");
        for(Subsidiary item:items)
        {
            System.out.println(item);
        }
        System.out.println(Subsidiary.count(""));
    } 
}

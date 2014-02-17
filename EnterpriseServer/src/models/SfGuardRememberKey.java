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

public class SfGuardRememberKey {
    //------------FIELDS-----------
    public static final String tablename="sf_guard_remember_key";
    //field names
    public static String[] fields={
            "id"
            ,"user_id"
            ,"remember_key"
            ,"ip_address"
            ,"created_at"
            ,"updated_at"
            };
    //field types
    public static String[] fieldtypes={
            "bigint(20)"
            ,"bigint(20)"
            ,"varchar(32)"
            ,"varchar(50)"
            ,"datetime"
            ,"datetime"
            };
    //-----------------------

    public Long id;
    public Long user_id;
    public String remember_key;
    public String ip_address;
    public Timestamp created_at;
    public Timestamp updated_at;

    public SfGuardRememberKey() {
    }
    public SfGuardRememberKey(ResultSet rs) {
        try {
            id=rs.getLong("id");
            user_id=rs.getLong("user_id");
            remember_key=rs.getString("remember_key");
            ip_address=rs.getString("ip_address");
            created_at=rs.getTimestamp("created_at");
            updated_at=rs.getTimestamp("updated_at");
        } catch (SQLException ex) {
            Logger.getLogger(SfGuardRememberKey.class.getName()).log(Level.SEVERE, null, ex);
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

    public Long getUserId() {
            return user_id;
    }

    public void setUserId(Long user_id) {
            this.user_id = user_id;
    }

    public String getRememberKey() {
            return remember_key;
    }

    public void setRememberKey(String remember_key) {
            this.remember_key = remember_key;
    }

    public String getIpAddress() {
            return ip_address;
    }

    public void setIpAddress(String ip_address) {
            this.ip_address = ip_address;
    }

    public Timestamp getCreatedAt() {
            return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
            this.created_at = created_at;
    }

    public Timestamp getUpdatedAt() {
            return updated_at;
    }

    public void setUpdatedAt(Timestamp updated_at) {
            this.updated_at = updated_at;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(user_id.toString());
            values.add(remember_key);
            values.add(ip_address);
            values.add(created_at.toString());
            values.add(updated_at.toString());

            return values;
    }
    public void delete()
    {
            SfGuardRememberKey.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    SfGuardRememberKey.insert(this);
            else
                    SfGuardRememberKey.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static SfGuardRememberKey getByName(String name)
    {
            HashMap<Long,SfGuardRememberKey> map=select(" name = '"+name+"'");
            for(SfGuardRememberKey item:map)return item;
            return null;
    }	
    */
    public static SfGuardRememberKey getById(Long id) {
            ArrayList<SfGuardRememberKey> map=select(" id = '"+id.toString()+"'");
            for(SfGuardRememberKey item:map)return item;
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
            Logger.getLogger(SfGuardRememberKey.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(SfGuardRememberKey item)
    {
        delete(item.getId());
    }
    public static void insert(SfGuardRememberKey item)
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
            Logger.getLogger(SfGuardRememberKey.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(SfGuardRememberKey item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(SfGuardRememberKey.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SfGuardRememberKey.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            return null;
    }

    public static ArrayList<SfGuardRememberKey> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<SfGuardRememberKey> items=new ArrayList<SfGuardRememberKey>();
            while (rs.next()) {
                items.add(new SfGuardRememberKey(rs));
                    //items.put(rs.getLong("id"), new SfGuardRememberKey(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(SfGuardRememberKey.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(SfGuardRememberKey item,boolean withId)
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
    public static String implodeFieldsWithValues(SfGuardRememberKey item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("SfGuardRememberKey:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<SfGuardRememberKey> items=SfGuardRememberKey.select("");
        for(SfGuardRememberKey item:items)
        {
            System.out.println(item);
        }
        System.out.println(SfGuardRememberKey.count(""));
    } 
}

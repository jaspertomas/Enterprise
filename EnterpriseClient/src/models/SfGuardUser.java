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

public class SfGuardUser {
    //------------FIELDS-----------
    public static final String tablename="sf_guard_user";
    //field names
    public static String[] fields={
            "id"
            ,"first_name"
            ,"last_name"
            ,"email_address"
            ,"username"
            ,"algorithm"
            ,"salt"
            ,"password"
            ,"is_active"
            ,"is_super_admin"
            ,"last_login"
            ,"created_at"
            ,"updated_at"
            };
    //field types
    public static String[] fieldtypes={
            "bigint(20)"
            ,"varchar(255)"
            ,"varchar(255)"
            ,"varchar(255)"
            ,"varchar(128)"
            ,"varchar(128)"
            ,"varchar(128)"
            ,"varchar(128)"
            ,"tinyint(1)"
            ,"tinyint(1)"
            ,"datetime"
            ,"datetime"
            ,"datetime"
            };
    //-----------------------

    public Long id;
    public String first_name;
    public String last_name;
    public String email_address;
    public String username;
    public String algorithm;
    public String salt;
    public String password;
    public Integer is_active;
    public Integer is_super_admin;
    public Timestamp last_login;
    public Timestamp created_at;
    public Timestamp updated_at;

    public SfGuardUser() {
    }
    public SfGuardUser(ResultSet rs) {
        try {
            id=rs.getLong("id");
            first_name=rs.getString("first_name");
            last_name=rs.getString("last_name");
            email_address=rs.getString("email_address");
            username=rs.getString("username");
            algorithm=rs.getString("algorithm");
            salt=rs.getString("salt");
            password=rs.getString("password");
            is_active=rs.getInt("is_active");
            is_super_admin=rs.getInt("is_super_admin");
            last_login=rs.getTimestamp("last_login");
            created_at=rs.getTimestamp("created_at");
            updated_at=rs.getTimestamp("updated_at");
        } catch (SQLException ex) {
            Logger.getLogger(SfGuardUser.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getFirstName() {
            return first_name;
    }

    public void setFirstName(String first_name) {
            this.first_name = first_name;
    }

    public String getLastName() {
            return last_name;
    }

    public void setLastName(String last_name) {
            this.last_name = last_name;
    }

    public String getEmailAddress() {
            return email_address;
    }

    public void setEmailAddress(String email_address) {
            this.email_address = email_address;
    }

    public String getUsername() {
            return username;
    }

    public void setUsername(String username) {
            this.username = username;
    }

    public String getAlgorithm() {
            return algorithm;
    }

    public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
    }

    public String getSalt() {
            return salt;
    }

    public void setSalt(String salt) {
            this.salt = salt;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public Integer getIsActive() {
            return is_active;
    }

    public void setIsActive(Integer is_active) {
            this.is_active = is_active;
    }

    public Integer getIsSuperAdmin() {
            return is_super_admin;
    }

    public void setIsSuperAdmin(Integer is_super_admin) {
            this.is_super_admin = is_super_admin;
    }

    public Timestamp getLastLogin() {
            return last_login;
    }

    public void setLastLogin(Timestamp last_login) {
            this.last_login = last_login;
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
            values.add(first_name);
            values.add(last_name);
            values.add(email_address);
            values.add(username);
            values.add(algorithm);
            values.add(salt);
            values.add(password);
            values.add(is_active.toString());
            values.add(is_super_admin.toString());
            values.add(last_login.toString());
            values.add(created_at.toString());
            values.add(updated_at.toString());

            return values;
    }
    public void delete()
    {
            SfGuardUser.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    SfGuardUser.insert(this);
            else
                    SfGuardUser.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static SfGuardUser getByName(String name)
    {
            HashMap<Long,SfGuardUser> map=select(" name = '"+name+"'");
            for(SfGuardUser item:map)return item;
            return null;
    }	
    */
    public static SfGuardUser getById(Long id) {
            ArrayList<SfGuardUser> map=select(" id = '"+id.toString()+"'");
            for(SfGuardUser item:map)return item;
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
            Logger.getLogger(SfGuardUser.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(SfGuardUser item)
    {
        delete(item.getId());
    }
    public static void insert(SfGuardUser item)
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
            Logger.getLogger(SfGuardUser.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(SfGuardUser item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(SfGuardUser.class.getName()).log(Level.SEVERE, null, ex);
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

    public static ArrayList<SfGuardUser> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<SfGuardUser> items=new ArrayList<SfGuardUser>();
            while (rs.next()) {
                items.add(new SfGuardUser(rs));
                    //items.put(rs.getLong("id"), new SfGuardUser(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(SfGuardUser.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(SfGuardUser item,boolean withId)
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
    public static String implodeFieldsWithValues(SfGuardUser item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("SfGuardUser:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<SfGuardUser> items=SfGuardUser.select("");
        for(SfGuardUser item:items)
        {
            System.out.println(item);
        }
        System.out.println(SfGuardUser.count(""));
    } 
}

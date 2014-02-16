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

public class Producttype {
    //------------FIELDS-----------
    public static final String tablename="producttype";
    //field names
    public static String[] fields={
            "id"
            ,"name"
            ,"description"
            ,"parent_id"
            ,"priority"
            ,"category1"
            ,"category2"
            ,"category3"
            ,"category4"
            ,"category5"
            ,"category6"
            ,"category7"
            ,"category8"
            ,"category9"
            ,"category10"
            ,"path_ids"
            ,"path"
            ,"notes"
            ,"status"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(20)"
            ,"varchar(100)"
            ,"int(11)"
            ,"int(11)"
            ,"varchar(30)"
            ,"varchar(30)"
            ,"varchar(30)"
            ,"varchar(30)"
            ,"varchar(30)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(50)"
            ,"text"
            ,"enum('Red','Orange','Yellow','Green','Blue','Indigo','Violet')"
            };
    //-----------------------

    public Integer id;
    public String name;
    public String description;
    public Integer parent_id;
    public Integer priority;
    public String category1;
    public String category2;
    public String category3;
    public String category4;
    public String category5;
    public String category6;
    public String category7;
    public String category8;
    public String category9;
    public String category10;
    public String path_ids;
    public String path;
    public String notes;
    public String status;

    public Producttype() {
    }
    public Producttype(ResultSet rs) {
        try {
            id=rs.getInt("id");
            name=rs.getString("name");
            description=rs.getString("description");
            parent_id=rs.getInt("parent_id");
            priority=rs.getInt("priority");
            category1=rs.getString("category1");
            category2=rs.getString("category2");
            category3=rs.getString("category3");
            category4=rs.getString("category4");
            category5=rs.getString("category5");
            category6=rs.getString("category6");
            category7=rs.getString("category7");
            category8=rs.getString("category8");
            category9=rs.getString("category9");
            category10=rs.getString("category10");
            path_ids=rs.getString("path_ids");
            path=rs.getString("path");
            notes=rs.getString("notes");
            status=rs.getString("status");
        } catch (SQLException ex) {
            Logger.getLogger(Producttype.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public Integer getParentId() {
            return parent_id;
    }

    public void setParentId(Integer parent_id) {
            this.parent_id = parent_id;
    }

    public Integer getPriority() {
            return priority;
    }

    public void setPriority(Integer priority) {
            this.priority = priority;
    }

    public String getCategory1() {
            return category1;
    }

    public void setCategory1(String category1) {
            this.category1 = category1;
    }

    public String getCategory2() {
            return category2;
    }

    public void setCategory2(String category2) {
            this.category2 = category2;
    }

    public String getCategory3() {
            return category3;
    }

    public void setCategory3(String category3) {
            this.category3 = category3;
    }

    public String getCategory4() {
            return category4;
    }

    public void setCategory4(String category4) {
            this.category4 = category4;
    }

    public String getCategory5() {
            return category5;
    }

    public void setCategory5(String category5) {
            this.category5 = category5;
    }

    public String getCategory6() {
            return category6;
    }

    public void setCategory6(String category6) {
            this.category6 = category6;
    }

    public String getCategory7() {
            return category7;
    }

    public void setCategory7(String category7) {
            this.category7 = category7;
    }

    public String getCategory8() {
            return category8;
    }

    public void setCategory8(String category8) {
            this.category8 = category8;
    }

    public String getCategory9() {
            return category9;
    }

    public void setCategory9(String category9) {
            this.category9 = category9;
    }

    public String getCategory10() {
            return category10;
    }

    public void setCategory10(String category10) {
            this.category10 = category10;
    }

    public String getPathIds() {
            return path_ids;
    }

    public void setPathIds(String path_ids) {
            this.path_ids = path_ids;
    }

    public String getPath() {
            return path;
    }

    public void setPath(String path) {
            this.path = path;
    }

    public String getNotes() {
            return notes;
    }

    public void setNotes(String notes) {
            this.notes = notes;
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
            values.add(name);
            values.add(description);
            values.add(parent_id.toString());
            values.add(priority.toString());
            values.add(category1);
            values.add(category2);
            values.add(category3);
            values.add(category4);
            values.add(category5);
            values.add(category6);
            values.add(category7);
            values.add(category8);
            values.add(category9);
            values.add(category10);
            values.add(path_ids);
            values.add(path);
            values.add(notes);
            values.add(status);

            return values;
    }
    public void delete()
    {
            Producttype.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Producttype.insert(this);
            else
                    Producttype.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Producttype getByName(String name)
    {
            HashMap<Integer,Producttype> map=select(" name = '"+name+"'");
            for(Producttype item:map.values())return item;
            return null;
    }	
    */
    public static Producttype getById(Integer id) {
            HashMap<Integer,Producttype> map=select(" id = '"+id.toString()+"'");
            for(Producttype item:map.values())return item;
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
            Logger.getLogger(Producttype.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Producttype item)
    {
        delete(item.getId());
    }
    public static void insert(Producttype item)
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
            Logger.getLogger(Producttype.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Producttype item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Producttype.class.getName()).log(Level.SEVERE, null, ex);
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

    public static HashMap<Integer, Producttype> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
            Connection conn=MySqlDBHelper.getInstance().getConnection();
            Statement st = null;
            ResultSet rs = null;
            try { 
                st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

                HashMap<Integer, Producttype> items=new HashMap<Integer, Producttype>();
                while (rs.next()) {
                    items.put(rs.getInt("id"), new Producttype(rs));
                }
                return items;
            } catch (SQLException ex) {
                Logger.getLogger(Producttype.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return null;
            }

    }
    //-----------database helper functions--------------
    public static String implodeValues(Producttype item,boolean withId)
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
    public static String implodeFieldsWithValues(Producttype item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Producttype:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        HashMap<Integer,Producttype> items=Producttype.select("");
        for(Integer key:items.keySet())
        {
            Producttype item=items.get(key);
            System.out.println(key);
            System.out.println(item);
        }
        System.out.println(Producttype.count(""));
    } 
}

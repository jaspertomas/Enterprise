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

public class Event {
    //------------FIELDS-----------
    public static final String tablename="event";
    //field names
    public static String[] fields={
            "id"
            ,"type"
            ,"parent_class"
            ,"parent_id"
            ,"parent_name"
            ,"child_class"
            ,"children_id"
            ,"date"
            ,"amount"
            ,"detail1"
            ,"detail2"
            ,"detail3"
            ,"notes"
            ,"is_cancelled"
            ,"checkcleardate"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"int(11)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(10)"
            ,"date"
            ,"decimal(10,2)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"text"
            ,"tinyint(4)"
            ,"date"
            };
    //-----------------------

    public Integer id;
    public String type;
    public String parent_class;
    public Integer parent_id;
    public String parent_name;
    public String child_class;
    public String children_id;
    public Date date;
    public BigDecimal amount;
    public String detail1;
    public String detail2;
    public String detail3;
    public String notes;
    public Integer is_cancelled;
    public Date checkcleardate;

    public Event() {
    }
    public Event(ResultSet rs) {
        try {
            id=rs.getInt("id");
            type=rs.getString("type");
            parent_class=rs.getString("parent_class");
            parent_id=rs.getInt("parent_id");
            parent_name=rs.getString("parent_name");
            child_class=rs.getString("child_class");
            children_id=rs.getString("children_id");
            date=rs.getDate("date");
            amount=rs.getBigDecimal("amount");
            detail1=rs.getString("detail1");
            detail2=rs.getString("detail2");
            detail3=rs.getString("detail3");
            notes=rs.getString("notes");
            is_cancelled=rs.getInt("is_cancelled");
            checkcleardate=rs.getDate("checkcleardate");
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getType() {
            return type;
    }

    public void setType(String type) {
            this.type = type;
    }

    public String getParentClass() {
            return parent_class;
    }

    public void setParentClass(String parent_class) {
            this.parent_class = parent_class;
    }

    public Integer getParentId() {
            return parent_id;
    }

    public void setParentId(Integer parent_id) {
            this.parent_id = parent_id;
    }

    public String getParentName() {
            return parent_name;
    }

    public void setParentName(String parent_name) {
            this.parent_name = parent_name;
    }

    public String getChildClass() {
            return child_class;
    }

    public void setChildClass(String child_class) {
            this.child_class = child_class;
    }

    public String getChildrenId() {
            return children_id;
    }

    public void setChildrenId(String children_id) {
            this.children_id = children_id;
    }

    public Date getDate() {
            return date;
    }

    public void setDate(Date date) {
            this.date = date;
    }

    public BigDecimal getAmount() {
            return amount;
    }

    public void setAmount(BigDecimal amount) {
            this.amount = amount;
    }

    public String getDetail1() {
            return detail1;
    }

    public void setDetail1(String detail1) {
            this.detail1 = detail1;
    }

    public String getDetail2() {
            return detail2;
    }

    public void setDetail2(String detail2) {
            this.detail2 = detail2;
    }

    public String getDetail3() {
            return detail3;
    }

    public void setDetail3(String detail3) {
            this.detail3 = detail3;
    }

    public String getNotes() {
            return notes;
    }

    public void setNotes(String notes) {
            this.notes = notes;
    }

    public Integer getIsCancelled() {
            return is_cancelled;
    }

    public void setIsCancelled(Integer is_cancelled) {
            this.is_cancelled = is_cancelled;
    }

    public Date getCheckcleardate() {
            return checkcleardate;
    }

    public void setCheckcleardate(Date checkcleardate) {
            this.checkcleardate = checkcleardate;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(type);
            values.add(parent_class);
            values.add(parent_id.toString());
            values.add(parent_name);
            values.add(child_class);
            values.add(children_id);
            values.add(date.toString());
            values.add(amount.toString());
            values.add(detail1);
            values.add(detail2);
            values.add(detail3);
            values.add(notes);
            values.add(is_cancelled.toString());
            values.add(checkcleardate.toString());

            return values;
    }
    public void delete()
    {
            Event.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Event.insert(this);
            else
                    Event.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Event getByName(String name)
    {
            HashMap<Integer,Event> map=select(" name = '"+name+"'");
            for(Event item:map.values())return item;
            return null;
    }	
    */
    public static Event getById(Integer id) {
            HashMap<Integer,Event> map=select(" id = '"+id.toString()+"'");
            for(Event item:map.values())return item;
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
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Event item)
    {
        delete(item.getId());
    }
    public static void insert(Event item)
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
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Event item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static HashMap<Integer, Event> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
            Connection conn=MySqlDBHelper.getInstance().getConnection();
            Statement st = null;
            ResultSet rs = null;
            try { 
                st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

                HashMap<Integer, Event> items=new HashMap<Integer, Event>();
                while (rs.next()) {
                    items.put(rs.getInt("id"), new Event(rs));
                }
                return items;
            } catch (SQLException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return null;
            }

    }
    //-----------database helper functions--------------
    public static String implodeValues(Event item,boolean withId)
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
    public static String implodeFieldsWithValues(Event item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Event:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        HashMap<Integer,Event> items=Event.select("");
        for(Integer key:items.keySet())
        {
            Event item=items.get(key);
            System.out.println(key);
            System.out.println(item);
        }
    } 
}

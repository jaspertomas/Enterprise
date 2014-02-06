package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ServerProtocol;
import utils.MySqlDBHelper;

public class Apps {
	//------------CONSTANTS-----------
	public static final String tablename="apps";
	public static String[] fields={
		"id"
		,"name"
		,"componentname"
		};
	public static String[] fieldtypes={
		"varchar(40)"
		,"varchar(100)"
		,"varchar(500)"
		};//database types, for use by MyDatabaseHelper
	//-----------------------
/*
	private static HashMap<String, App> items = null;

	// to be called after update, when it is possible that database has been
	// altered
	public static void reset() {
		items = null;
	}
	public static HashMap<String, App> getItems() {
		if (items == null) {
			SQLiteDatabase db = MyDatabaseHelper.getInstance()
					.getWritableDatabase();

			Cursor cursor = db.rawQuery("SELECT * FROM "+tablename, null);
			items = new HashMap<String, App>();
			while (cursor.moveToNext()) {
				items.put(cursor.getString(0), new App(cursor));
			}
		}
		return items;
	}
*/
	//-----------getter functions----------
        /*
	public static App getByName(String name)
	{
		//initialize if not yet initialized
		getItems();
		
		for(App item:items.values())
		{
			if(item.getName().contentEquals(name))
				return item;
		}
		return null;
	}	
	*/
	public static App getById(String id) {
                HashMap<String,App> map=select(" id = '"+id+"'");
                for(App app:map.values())return app;
                return null;
	}
	//-----------database functions--------------
        
	public static void delete(String id)
	{
            Connection conn=MySqlDBHelper.getInstance().getConnection();            
            Statement st = null;
            try { 
                st = conn.createStatement();
                st.executeUpdate("delete from "+tablename+" where id = '"+id+"';");
            } catch (SQLException ex) {
                Logger.getLogger(ServerProtocol.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
	}
	public static void delete(App item)
	{
            delete(item.getId());
	}
	public static void insert(App item)
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
                Logger.getLogger(ServerProtocol.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
	}
	public static void update(App item)
	{
            Connection conn=MySqlDBHelper.getInstance().getConnection();            
            Statement st = null;
            boolean withid=false;
            try { 
                st = conn.createStatement();
		st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId()+"';");
            } catch (SQLException ex) {
                Logger.getLogger(ServerProtocol.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
	}
        public static HashMap<String, App> select(String conditions)
        {
            if(conditions.isEmpty())conditions = "1";
                Connection conn=MySqlDBHelper.getInstance().getConnection();
                Statement st = null;
                ResultSet rs = null;
                try { 
                    st = conn.createStatement();
    //                rs = st.executeQuery("SELECT VERSION()");
                    rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

                    HashMap<String, App> items=new HashMap<String, App>();
                    while (rs.next()) {
                        items.put(rs.getString("id"), new App(rs));
                    }
                    return items;
                } catch (SQLException ex) {
                    Logger.getLogger(ServerProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    return null;
                }
        
        }
	//-----------database helper functions--------------
	public static String implodeValues(App item,boolean withId)
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
	public static String implodeFieldsWithValues(App item,boolean withId)
	{
		ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.
		
		if(values.size()!=fields.length)
		{
			System.err.println("Apps:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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
}

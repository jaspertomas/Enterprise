package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

	String username="",password_hash="",id="";
	public User() {
	}
	public User(ResultSet rs) {
            try {
                id=rs.getString("id");
                username = rs.getString("username");
                password_hash = rs.getString("password_hash");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
//	public String getUuid()
//	{
//		return "app-"+id.toString()+"-";
//	}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword_hash() {
            return password_hash;
        }

        public void setPassword_hash(String password_hash) {
            this.password_hash = password_hash;
        }



        
        
        
	//database functions
	public ArrayList<String> implodeFieldValuesHelper(boolean withId)
	{
		ArrayList<String> values=new ArrayList<String>(); 
		if(withId)values.add(id.toString());

		//add values for each field here
		values.add(username);
		values.add(password_hash);
		
		return values;
	}
	public void delete()
	{
		Users.delete(this);
	}
	public void save()
	{
		//if(Users.getById(id)==null)
		if(id==null || id.toString().isEmpty() || id.toString().contentEquals("0"))
			Users.insert(this);
		else
			Users.update(this);
	}
	public String toString()
	{
		return username;
	}
//	public ContentValues insertValues()
//	{
//		ContentValues insertValues = new ContentValues();
//
////		"id"
////		,"name"
////		,"componentname"
//		
//		//add id only if id is varchar
//		//assuming id types are only integer and varchar
////		if(!Users.fieldtypes[0].contentEquals("integer"))
//		insertValues.put(Users.fields[0], id);
//		insertValues.put(Users.fields[1], name);
//		insertValues.put(Users.fields[2], componentname);
//		return insertValues;
//	}		
//
//	public void save()
//	{
//		if(id.isEmpty())
//			//for varchar ids - id is already known before save
//			Users.insert(this);
//			//for string ids
////			id=Users.insert(this);
//		else
//			Users.update(this);
//	}
        
}

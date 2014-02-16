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

public class Customer {
    //------------FIELDS-----------
    public static final String tablename="customer";
    //field names
    public static String[] fields={
            "id"
            ,"name"
            ,"tin_no"
            ,"address"
            ,"phone1"
            ,"phone2"
            ,"faxnum"
            ,"email"
            ,"note"
            ,"rep"
            ,"repno"
            ,"rep2"
            ,"rep2no"
            ,"taxitem"
            ,"notepad"
            ,"salutation"
            ,"is_suki"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"char(60)"
            ,"varchar(15)"
            ,"varchar(100)"
            ,"char(60)"
            ,"char(60)"
            ,"char(60)"
            ,"char(60)"
            ,"char(60)"
            ,"varchar(60)"
            ,"varchar(50)"
            ,"varchar(60)"
            ,"varchar(50)"
            ,"char(60)"
            ,"char(60)"
            ,"char(60)"
            ,"tinyint(11)"
            };
    //-----------------------

    public Integer id;
    public String name;
    public String tin_no;
    public String address;
    public String phone1;
    public String phone2;
    public String faxnum;
    public String email;
    public String note;
    public String rep;
    public String repno;
    public String rep2;
    public String rep2no;
    public String taxitem;
    public String notepad;
    public String salutation;
    public Integer is_suki;

    public Customer() {
    }
    public Customer(ResultSet rs) {
        try {
            id=rs.getInt("id");
            name=rs.getString("name");
            tin_no=rs.getString("tin_no");
            address=rs.getString("address");
            phone1=rs.getString("phone1");
            phone2=rs.getString("phone2");
            faxnum=rs.getString("faxnum");
            email=rs.getString("email");
            note=rs.getString("note");
            rep=rs.getString("rep");
            repno=rs.getString("repno");
            rep2=rs.getString("rep2");
            rep2no=rs.getString("rep2no");
            taxitem=rs.getString("taxitem");
            notepad=rs.getString("notepad");
            salutation=rs.getString("salutation");
            is_suki=rs.getInt("is_suki");
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getTinNo() {
            return tin_no;
    }

    public void setTinNo(String tin_no) {
            this.tin_no = tin_no;
    }

    public String getAddress() {
            return address;
    }

    public void setAddress(String address) {
            this.address = address;
    }

    public String getPhone1() {
            return phone1;
    }

    public void setPhone1(String phone1) {
            this.phone1 = phone1;
    }

    public String getPhone2() {
            return phone2;
    }

    public void setPhone2(String phone2) {
            this.phone2 = phone2;
    }

    public String getFaxnum() {
            return faxnum;
    }

    public void setFaxnum(String faxnum) {
            this.faxnum = faxnum;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String getNote() {
            return note;
    }

    public void setNote(String note) {
            this.note = note;
    }

    public String getRep() {
            return rep;
    }

    public void setRep(String rep) {
            this.rep = rep;
    }

    public String getRepno() {
            return repno;
    }

    public void setRepno(String repno) {
            this.repno = repno;
    }

    public String getRep2() {
            return rep2;
    }

    public void setRep2(String rep2) {
            this.rep2 = rep2;
    }

    public String getRep2no() {
            return rep2no;
    }

    public void setRep2no(String rep2no) {
            this.rep2no = rep2no;
    }

    public String getTaxitem() {
            return taxitem;
    }

    public void setTaxitem(String taxitem) {
            this.taxitem = taxitem;
    }

    public String getNotepad() {
            return notepad;
    }

    public void setNotepad(String notepad) {
            this.notepad = notepad;
    }

    public String getSalutation() {
            return salutation;
    }

    public void setSalutation(String salutation) {
            this.salutation = salutation;
    }

    public Integer getIsSuki() {
            return is_suki;
    }

    public void setIsSuki(Integer is_suki) {
            this.is_suki = is_suki;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(name);
            values.add(tin_no);
            values.add(address);
            values.add(phone1);
            values.add(phone2);
            values.add(faxnum);
            values.add(email);
            values.add(note);
            values.add(rep);
            values.add(repno);
            values.add(rep2);
            values.add(rep2no);
            values.add(taxitem);
            values.add(notepad);
            values.add(salutation);
            values.add(is_suki.toString());

            return values;
    }
    public void delete()
    {
            Customer.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Customer.insert(this);
            else
                    Customer.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Customer getByName(String name)
    {
            HashMap<Integer,Customer> map=select(" name = '"+name+"'");
            for(Customer item:map)return item;
            return null;
    }	
    */
    public static Customer getById(Integer id) {
            ArrayList<Customer> map=select(" id = '"+id.toString()+"'");
            for(Customer item:map)return item;
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
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Customer item)
    {
        delete(item.getId());
    }
    public static void insert(Customer item)
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
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Customer item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
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

    public static ArrayList<Customer> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<Customer> items=new ArrayList<Customer>();
            while (rs.next()) {
                items.add(new Customer(rs));
                    //items.put(rs.getInt("id"), new Customer(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(Customer item,boolean withId)
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
    public static String implodeFieldsWithValues(Customer item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Customer:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<Customer> items=Customer.select("");
        for(Customer item:items)
        {
            System.out.println(item);
        }
        System.out.println(Customer.count(""));
    } 
}

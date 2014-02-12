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

public class Quote {
    //------------FIELDS-----------
    public static final String tablename="quote";
    //field names
    public static String[] fields={
            "id"
            ,"date"
            ,"vendor_id"
            ,"product_id"
            ,"price"
            ,"discrate"
            ,"discamt"
            ,"ref_class"
            ,"ref_id"
            ,"total"
            ,"mine"
            ,"is_cancelled"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"date"
            ,"int(11)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"varchar(30)"
            ,"decimal(10,2)"
            ,"varchar(20)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"tinyint(4)"
            ,"tinyint(4)"
            };
    //-----------------------

    public Integer id;
    public Date date;
    public Integer vendor_id;
    public Integer product_id;
    public BigDecimal price;
    public String discrate;
    public BigDecimal discamt;
    public String ref_class;
    public Integer ref_id;
    public BigDecimal total;
    public Integer mine;
    public Integer is_cancelled;

    public Quote() {
    }
    public Quote(ResultSet rs) {
        try {
            id=rs.getInt("id");
            date=rs.getDate("date");
            vendor_id=rs.getInt("vendor_id");
            product_id=rs.getInt("product_id");
            price=rs.getBigDecimal("price");
            discrate=rs.getString("discrate");
            discamt=rs.getBigDecimal("discamt");
            ref_class=rs.getString("ref_class");
            ref_id=rs.getInt("ref_id");
            total=rs.getBigDecimal("total");
            mine=rs.getInt("mine");
            is_cancelled=rs.getInt("is_cancelled");
        } catch (SQLException ex) {
            Logger.getLogger(Quote.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getVendorId() {
            return vendor_id;
    }

    public void setVendorId(Integer vendor_id) {
            this.vendor_id = vendor_id;
    }

    public Integer getProductId() {
            return product_id;
    }

    public void setProductId(Integer product_id) {
            this.product_id = product_id;
    }

    public BigDecimal getPrice() {
            return price;
    }

    public void setPrice(BigDecimal price) {
            this.price = price;
    }

    public String getDiscrate() {
            return discrate;
    }

    public void setDiscrate(String discrate) {
            this.discrate = discrate;
    }

    public BigDecimal getDiscamt() {
            return discamt;
    }

    public void setDiscamt(BigDecimal discamt) {
            this.discamt = discamt;
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

    public BigDecimal getTotal() {
            return total;
    }

    public void setTotal(BigDecimal total) {
            this.total = total;
    }

    public Integer getMine() {
            return mine;
    }

    public void setMine(Integer mine) {
            this.mine = mine;
    }

    public Integer getIsCancelled() {
            return is_cancelled;
    }

    public void setIsCancelled(Integer is_cancelled) {
            this.is_cancelled = is_cancelled;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(date.toString());
            values.add(vendor_id.toString());
            values.add(product_id.toString());
            values.add(price.toString());
            values.add(discrate);
            values.add(discamt.toString());
            values.add(ref_class);
            values.add(ref_id.toString());
            values.add(total.toString());
            values.add(mine.toString());
            values.add(is_cancelled.toString());

            return values;
    }
    public void delete()
    {
            Quote.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Quote.insert(this);
            else
                    Quote.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Quote getByName(String name)
    {
            HashMap<Integer,Quote> map=select(" name = '"+name+"'");
            for(Quote item:map.values())return item;
            return null;
    }	
    */
    public static Quote getById(Integer id) {
            HashMap<Integer,Quote> map=select(" id = '"+id.toString()+"'");
            for(Quote item:map.values())return item;
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
            Logger.getLogger(Quote.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Quote item)
    {
        delete(item.getId());
    }
    public static void insert(Quote item)
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
            Logger.getLogger(Quote.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Quote item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Quote.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static HashMap<Integer, Quote> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
            Connection conn=MySqlDBHelper.getInstance().getConnection();
            Statement st = null;
            ResultSet rs = null;
            try { 
                st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

                HashMap<Integer, Quote> items=new HashMap<Integer, Quote>();
                while (rs.next()) {
                    items.put(rs.getInt("id"), new Quote(rs));
                }
                return items;
            } catch (SQLException ex) {
                Logger.getLogger(Quote.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return null;
            }

    }
    //-----------database helper functions--------------
    public static String implodeValues(Quote item,boolean withId)
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
    public static String implodeFieldsWithValues(Quote item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Quote:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        HashMap<Integer,Quote> items=Quote.select("");
        for(Integer key:items.keySet())
        {
            Quote item=items.get(key);
            System.out.println(key);
            System.out.println(item);
        }
    } 
}

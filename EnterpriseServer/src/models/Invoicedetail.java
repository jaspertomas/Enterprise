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

public class Invoicedetail {
    //------------FIELDS-----------
    public static final String tablename="invoicedetail";
    //field names
    public static String[] fields={
            "id"
            ,"invoice_id"
            ,"product_id"
            ,"barcode"
            ,"description"
            ,"qty"
            ,"price"
            ,"total"
            ,"discrate"
            ,"discamt"
            ,"unittotal"
            ,"is_cancelled"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"varchar(13)"
            ,"varchar(100)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"varchar(30)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"tinyint(4)"
            };
    //-----------------------

    public Integer id;
    public Integer invoice_id;
    public Integer product_id;
    public String barcode;
    public String description;
    public BigDecimal qty;
    public BigDecimal price;
    public BigDecimal total;
    public String discrate;
    public BigDecimal discamt;
    public BigDecimal unittotal;
    public Integer is_cancelled;

    public Invoicedetail() {
    }
    public Invoicedetail(ResultSet rs) {
        try {
            id=rs.getInt("id");
            invoice_id=rs.getInt("invoice_id");
            product_id=rs.getInt("product_id");
            barcode=rs.getString("barcode");
            description=rs.getString("description");
            qty=rs.getBigDecimal("qty");
            price=rs.getBigDecimal("price");
            total=rs.getBigDecimal("total");
            discrate=rs.getString("discrate");
            discamt=rs.getBigDecimal("discamt");
            unittotal=rs.getBigDecimal("unittotal");
            is_cancelled=rs.getInt("is_cancelled");
        } catch (SQLException ex) {
            Logger.getLogger(Invoicedetail.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getInvoiceId() {
            return invoice_id;
    }

    public void setInvoiceId(Integer invoice_id) {
            this.invoice_id = invoice_id;
    }

    public Integer getProductId() {
            return product_id;
    }

    public void setProductId(Integer product_id) {
            this.product_id = product_id;
    }

    public String getBarcode() {
            return barcode;
    }

    public void setBarcode(String barcode) {
            this.barcode = barcode;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public BigDecimal getQty() {
            return qty;
    }

    public void setQty(BigDecimal qty) {
            this.qty = qty;
    }

    public BigDecimal getPrice() {
            return price;
    }

    public void setPrice(BigDecimal price) {
            this.price = price;
    }

    public BigDecimal getTotal() {
            return total;
    }

    public void setTotal(BigDecimal total) {
            this.total = total;
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

    public BigDecimal getUnittotal() {
            return unittotal;
    }

    public void setUnittotal(BigDecimal unittotal) {
            this.unittotal = unittotal;
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
            values.add(invoice_id.toString());
            values.add(product_id.toString());
            values.add(barcode);
            values.add(description);
            values.add(qty.toString());
            values.add(price.toString());
            values.add(total.toString());
            values.add(discrate);
            values.add(discamt.toString());
            values.add(unittotal.toString());
            values.add(is_cancelled.toString());

            return values;
    }
    public void delete()
    {
            Invoicedetail.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Invoicedetail.insert(this);
            else
                    Invoicedetail.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Invoicedetail getByName(String name)
    {
            HashMap<Integer,Invoicedetail> map=select(" name = '"+name+"'");
            for(Invoicedetail item:map)return item;
            return null;
    }	
    */
    public static Invoicedetail getById(Integer id) {
            ArrayList<Invoicedetail> map=select(" id = '"+id.toString()+"'");
            for(Invoicedetail item:map)return item;
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
            Logger.getLogger(Invoicedetail.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Invoicedetail item)
    {
        delete(item.getId());
    }
    public static void insert(Invoicedetail item)
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
            Logger.getLogger(Invoicedetail.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Invoicedetail item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Invoicedetail.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Invoicedetail.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            return null;
    }

    public static ArrayList<Invoicedetail> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<Invoicedetail> items=new ArrayList<Invoicedetail>();
            while (rs.next()) {
                items.add(new Invoicedetail(rs));
                    //items.put(rs.getInt("id"), new Invoicedetail(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(Invoicedetail.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(Invoicedetail item,boolean withId)
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
    public static String implodeFieldsWithValues(Invoicedetail item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Invoicedetail:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<Invoicedetail> items=Invoicedetail.select("");
        for(Invoicedetail item:items)
        {
            System.out.println(item);
        }
        System.out.println(Invoicedetail.count(""));
    } 
}

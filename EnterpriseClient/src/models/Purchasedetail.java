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

public class Purchasedetail {
    //------------FIELDS-----------
    public static final String tablename="purchasedetail";
    //field names
    public static String[] fields={
            "id"
            ,"purchase_id"
            ,"description"
            ,"qty"
            ,"price"
            ,"sellprice"
            ,"total"
            ,"tax"
            ,"product_id"
            ,"barcode"
            ,"discrate"
            ,"discamt"
            ,"unittotal"
            ,"is_cancelled"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"varchar(100)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"int(11)"
            ,"varchar(13)"
            ,"varchar(30)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"tinyint(4)"
            };
    //-----------------------

    public Integer id;
    public Integer purchase_id;
    public String description;
    public BigDecimal qty;
    public BigDecimal price;
    public BigDecimal sellprice;
    public BigDecimal total;
    public BigDecimal tax;
    public Integer product_id;
    public String barcode;
    public String discrate;
    public BigDecimal discamt;
    public BigDecimal unittotal;
    public Integer is_cancelled;

    public Purchasedetail() {
    }
    public Purchasedetail(ResultSet rs) {
        try {
            id=rs.getInt("id");
            purchase_id=rs.getInt("purchase_id");
            description=rs.getString("description");
            qty=rs.getBigDecimal("qty");
            price=rs.getBigDecimal("price");
            sellprice=rs.getBigDecimal("sellprice");
            total=rs.getBigDecimal("total");
            tax=rs.getBigDecimal("tax");
            product_id=rs.getInt("product_id");
            barcode=rs.getString("barcode");
            discrate=rs.getString("discrate");
            discamt=rs.getBigDecimal("discamt");
            unittotal=rs.getBigDecimal("unittotal");
            is_cancelled=rs.getInt("is_cancelled");
        } catch (SQLException ex) {
            Logger.getLogger(Purchasedetail.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getPurchaseId() {
            return purchase_id;
    }

    public void setPurchaseId(Integer purchase_id) {
            this.purchase_id = purchase_id;
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

    public BigDecimal getSellprice() {
            return sellprice;
    }

    public void setSellprice(BigDecimal sellprice) {
            this.sellprice = sellprice;
    }

    public BigDecimal getTotal() {
            return total;
    }

    public void setTotal(BigDecimal total) {
            this.total = total;
    }

    public BigDecimal getTax() {
            return tax;
    }

    public void setTax(BigDecimal tax) {
            this.tax = tax;
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
            values.add(purchase_id.toString());
            values.add(description);
            values.add(qty.toString());
            values.add(price.toString());
            values.add(sellprice.toString());
            values.add(total.toString());
            values.add(tax.toString());
            values.add(product_id.toString());
            values.add(barcode);
            values.add(discrate);
            values.add(discamt.toString());
            values.add(unittotal.toString());
            values.add(is_cancelled.toString());

            return values;
    }
    public void delete()
    {
            Purchasedetail.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Purchasedetail.insert(this);
            else
                    Purchasedetail.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Purchasedetail getByName(String name)
    {
            HashMap<Integer,Purchasedetail> map=select(" name = '"+name+"'");
            for(Purchasedetail item:map.values())return item;
            return null;
    }	
    */
    public static Purchasedetail getById(Integer id) {
            HashMap<Integer,Purchasedetail> map=select(" id = '"+id.toString()+"'");
            for(Purchasedetail item:map.values())return item;
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
            Logger.getLogger(Purchasedetail.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Purchasedetail item)
    {
        delete(item.getId());
    }
    public static void insert(Purchasedetail item)
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
            Logger.getLogger(Purchasedetail.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Purchasedetail item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Purchasedetail.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static HashMap<Integer, Purchasedetail> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
            Connection conn=MySqlDBHelper.getInstance().getConnection();
            Statement st = null;
            ResultSet rs = null;
            try { 
                st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

                HashMap<Integer, Purchasedetail> items=new HashMap<Integer, Purchasedetail>();
                while (rs.next()) {
                    items.put(rs.getInt("id"), new Purchasedetail(rs));
                }
                return items;
            } catch (SQLException ex) {
                Logger.getLogger(Purchasedetail.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return null;
            }

    }
    //-----------database helper functions--------------
    public static String implodeValues(Purchasedetail item,boolean withId)
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
    public static String implodeFieldsWithValues(Purchasedetail item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Purchasedetail:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        HashMap<Integer,Purchasedetail> items=Purchasedetail.select("");
        for(Integer key:items.keySet())
        {
            Purchasedetail item=items.get(key);
            System.out.println(key);
            System.out.println(item);
        }
    } 
}

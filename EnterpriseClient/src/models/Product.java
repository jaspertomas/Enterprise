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

public class Product {
    //------------FIELDS-----------
    public static final String tablename="product";
    //field names
    public static String[] fields={
            "id"
            ,"name"
            ,"brand_id"
            ,"producttype_id"
            ,"qty"
            ,"minbuyprice"
            ,"maxbuyprice"
            ,"minsellprice"
            ,"maxsellprice"
            ,"description"
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
            ,"publish"
            ,"autocalcsellprice"
            ,"autocalcbuyprice"
            ,"monitored"
            ,"barcode"
            ,"is_service"
            ,"created_at"
            ,"updated_at"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(100)"
            ,"int(11)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"text"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"tinyint(4)"
            ,"tinyint(4)"
            ,"tinyint(4)"
            ,"tinyint(4)"
            ,"varchar(13)"
            ,"tinyint(4)"
            ,"datetime"
            ,"datetime"
            };
    //-----------------------

    public Integer id;
    public String name;
    public Integer brand_id;
    public Integer producttype_id;
    public BigDecimal qty;
    public BigDecimal minbuyprice;
    public BigDecimal maxbuyprice;
    public BigDecimal minsellprice;
    public BigDecimal maxsellprice;
    public String description;
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
    public Integer publish;
    public Integer autocalcsellprice;
    public Integer autocalcbuyprice;
    public Integer monitored;
    public String barcode;
    public Integer is_service;
    public Timestamp created_at;
    public Timestamp updated_at;

    public Product() {
    }
    public Product(ResultSet rs) {
        try {
            id=rs.getInt("id");
            name=rs.getString("name");
            brand_id=rs.getInt("brand_id");
            producttype_id=rs.getInt("producttype_id");
            qty=rs.getBigDecimal("qty");
            minbuyprice=rs.getBigDecimal("minbuyprice");
            maxbuyprice=rs.getBigDecimal("maxbuyprice");
            minsellprice=rs.getBigDecimal("minsellprice");
            maxsellprice=rs.getBigDecimal("maxsellprice");
            description=rs.getString("description");
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
            publish=rs.getInt("publish");
            autocalcsellprice=rs.getInt("autocalcsellprice");
            autocalcbuyprice=rs.getInt("autocalcbuyprice");
            monitored=rs.getInt("monitored");
            barcode=rs.getString("barcode");
            is_service=rs.getInt("is_service");
            created_at=rs.getTimestamp("created_at");
            updated_at=rs.getTimestamp("updated_at");
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getBrandId() {
            return brand_id;
    }

    public void setBrandId(Integer brand_id) {
            this.brand_id = brand_id;
    }

    public Integer getProducttypeId() {
            return producttype_id;
    }

    public void setProducttypeId(Integer producttype_id) {
            this.producttype_id = producttype_id;
    }

    public BigDecimal getQty() {
            return qty;
    }

    public void setQty(BigDecimal qty) {
            this.qty = qty;
    }

    public BigDecimal getMinbuyprice() {
            return minbuyprice;
    }

    public void setMinbuyprice(BigDecimal minbuyprice) {
            this.minbuyprice = minbuyprice;
    }

    public BigDecimal getMaxbuyprice() {
            return maxbuyprice;
    }

    public void setMaxbuyprice(BigDecimal maxbuyprice) {
            this.maxbuyprice = maxbuyprice;
    }

    public BigDecimal getMinsellprice() {
            return minsellprice;
    }

    public void setMinsellprice(BigDecimal minsellprice) {
            this.minsellprice = minsellprice;
    }

    public BigDecimal getMaxsellprice() {
            return maxsellprice;
    }

    public void setMaxsellprice(BigDecimal maxsellprice) {
            this.maxsellprice = maxsellprice;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
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

    public Integer getPublish() {
            return publish;
    }

    public void setPublish(Integer publish) {
            this.publish = publish;
    }

    public Integer getAutocalcsellprice() {
            return autocalcsellprice;
    }

    public void setAutocalcsellprice(Integer autocalcsellprice) {
            this.autocalcsellprice = autocalcsellprice;
    }

    public Integer getAutocalcbuyprice() {
            return autocalcbuyprice;
    }

    public void setAutocalcbuyprice(Integer autocalcbuyprice) {
            this.autocalcbuyprice = autocalcbuyprice;
    }

    public Integer getMonitored() {
            return monitored;
    }

    public void setMonitored(Integer monitored) {
            this.monitored = monitored;
    }

    public String getBarcode() {
            return barcode;
    }

    public void setBarcode(String barcode) {
            this.barcode = barcode;
    }

    public Integer getIsService() {
            return is_service;
    }

    public void setIsService(Integer is_service) {
            this.is_service = is_service;
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
            values.add(name);
            values.add(brand_id.toString());
            values.add(producttype_id.toString());
            values.add(qty.toString());
            values.add(minbuyprice.toString());
            values.add(maxbuyprice.toString());
            values.add(minsellprice.toString());
            values.add(maxsellprice.toString());
            values.add(description);
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
            values.add(publish.toString());
            values.add(autocalcsellprice.toString());
            values.add(autocalcbuyprice.toString());
            values.add(monitored.toString());
            values.add(barcode);
            values.add(is_service.toString());
            values.add(created_at.toString());
            values.add(updated_at.toString());

            return values;
    }
    public void delete()
    {
            Product.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Product.insert(this);
            else
                    Product.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Product getByName(String name)
    {
            HashMap<Integer,Product> map=select(" name = '"+name+"'");
            for(Product item:map)return item;
            return null;
    }	
    */
    public static Product getById(Integer id) {
            ArrayList<Product> map=select(" id = '"+id.toString()+"'");
            for(Product item:map)return item;
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
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Product item)
    {
        delete(item.getId());
    }
    public static void insert(Product item)
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
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Product item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
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

    public static ArrayList<Product> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<Product> items=new ArrayList<Product>();
            while (rs.next()) {
                items.add(new Product(rs));
                    //items.put(rs.getInt("id"), new Product(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(Product item,boolean withId)
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
    public static String implodeFieldsWithValues(Product item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Product:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<Product> items=Product.select("");
        for(Product item:items)
        {
            System.out.println(item);
        }
        System.out.println(Product.count(""));
    } 
}

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

public class Invoice {
    //------------FIELDS-----------
    public static final String tablename="invoice";
    //field names
    public static String[] fields={
            "id"
            ,"customer_id"
            ,"customer_name"
            ,"invno"
            ,"ponumber"
            ,"notes"
            ,"payonly"
            ,"total"
            ,"cheque"
            ,"chequedate"
            ,"date"
            ,"duedate"
            ,"datepaid"
            ,"terms_id"
            ,"salesman_id"
            ,"technician_id"
            ,"template_id"
            ,"cash"
            ,"chequeamt"
            ,"credit"
            ,"discrate"
            ,"discamt"
            ,"saletype"
            ,"status"
            ,"dsrdeduction"
            ,"balance"
            ,"chequedata"
            ,"checkcleardate"
            ,"checkcollectevents"
            ,"hidden"
            ,"is_inspected"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"varchar(50)"
            ,"varchar(20)"
            ,"varchar(20)"
            ,"text"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"varchar(100)"
            ,"date"
            ,"date"
            ,"date"
            ,"date"
            ,"int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"varchar(30)"
            ,"decimal(10,2)"
            ,"enum('Cash','Cheque','Account','Other')"
            ,"enum('Pending','Paid','Cancelled')"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"varchar(100)"
            ,"date"
            ,"tinyint(4)"
            ,"tinyint(4)"
            ,"tinyint(4)"
            };
    //-----------------------

    public Integer id;
    public Integer customer_id;
    public String customer_name;
    public String invno;
    public String ponumber;
    public String notes;
    public BigDecimal payonly;
    public BigDecimal total;
    public String cheque;
    public Date chequedate;
    public Date date;
    public Date duedate;
    public Date datepaid;
    public Integer terms_id;
    public Integer salesman_id;
    public Integer technician_id;
    public Integer template_id;
    public BigDecimal cash;
    public BigDecimal chequeamt;
    public BigDecimal credit;
    public String discrate;
    public BigDecimal discamt;
    public String saletype;
    public String status;
    public BigDecimal dsrdeduction;
    public BigDecimal balance;
    public String chequedata;
    public Date checkcleardate;
    public Integer checkcollectevents;
    public Integer hidden;
    public Integer is_inspected;

    public Invoice() {
    }
    public Invoice(ResultSet rs) {
        try {
            id=rs.getInt("id");
            customer_id=rs.getInt("customer_id");
            customer_name=rs.getString("customer_name");
            invno=rs.getString("invno");
            ponumber=rs.getString("ponumber");
            notes=rs.getString("notes");
            payonly=rs.getBigDecimal("payonly");
            total=rs.getBigDecimal("total");
            cheque=rs.getString("cheque");
            chequedate=rs.getDate("chequedate");
            date=rs.getDate("date");
            duedate=rs.getDate("duedate");
            datepaid=rs.getDate("datepaid");
            terms_id=rs.getInt("terms_id");
            salesman_id=rs.getInt("salesman_id");
            technician_id=rs.getInt("technician_id");
            template_id=rs.getInt("template_id");
            cash=rs.getBigDecimal("cash");
            chequeamt=rs.getBigDecimal("chequeamt");
            credit=rs.getBigDecimal("credit");
            discrate=rs.getString("discrate");
            discamt=rs.getBigDecimal("discamt");
            saletype=rs.getString("saletype");
            status=rs.getString("status");
            dsrdeduction=rs.getBigDecimal("dsrdeduction");
            balance=rs.getBigDecimal("balance");
            chequedata=rs.getString("chequedata");
            checkcleardate=rs.getDate("checkcleardate");
            checkcollectevents=rs.getInt("checkcollectevents");
            hidden=rs.getInt("hidden");
            is_inspected=rs.getInt("is_inspected");
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getCustomerId() {
            return customer_id;
    }

    public void setCustomerId(Integer customer_id) {
            this.customer_id = customer_id;
    }

    public String getCustomerName() {
            return customer_name;
    }

    public void setCustomerName(String customer_name) {
            this.customer_name = customer_name;
    }

    public String getInvno() {
            return invno;
    }

    public void setInvno(String invno) {
            this.invno = invno;
    }

    public String getPonumber() {
            return ponumber;
    }

    public void setPonumber(String ponumber) {
            this.ponumber = ponumber;
    }

    public String getNotes() {
            return notes;
    }

    public void setNotes(String notes) {
            this.notes = notes;
    }

    public BigDecimal getPayonly() {
            return payonly;
    }

    public void setPayonly(BigDecimal payonly) {
            this.payonly = payonly;
    }

    public BigDecimal getTotal() {
            return total;
    }

    public void setTotal(BigDecimal total) {
            this.total = total;
    }

    public String getCheque() {
            return cheque;
    }

    public void setCheque(String cheque) {
            this.cheque = cheque;
    }

    public Date getChequedate() {
            return chequedate;
    }

    public void setChequedate(Date chequedate) {
            this.chequedate = chequedate;
    }

    public Date getDate() {
            return date;
    }

    public void setDate(Date date) {
            this.date = date;
    }

    public Date getDuedate() {
            return duedate;
    }

    public void setDuedate(Date duedate) {
            this.duedate = duedate;
    }

    public Date getDatepaid() {
            return datepaid;
    }

    public void setDatepaid(Date datepaid) {
            this.datepaid = datepaid;
    }

    public Integer getTermsId() {
            return terms_id;
    }

    public void setTermsId(Integer terms_id) {
            this.terms_id = terms_id;
    }

    public Integer getSalesmanId() {
            return salesman_id;
    }

    public void setSalesmanId(Integer salesman_id) {
            this.salesman_id = salesman_id;
    }

    public Integer getTechnicianId() {
            return technician_id;
    }

    public void setTechnicianId(Integer technician_id) {
            this.technician_id = technician_id;
    }

    public Integer getTemplateId() {
            return template_id;
    }

    public void setTemplateId(Integer template_id) {
            this.template_id = template_id;
    }

    public BigDecimal getCash() {
            return cash;
    }

    public void setCash(BigDecimal cash) {
            this.cash = cash;
    }

    public BigDecimal getChequeamt() {
            return chequeamt;
    }

    public void setChequeamt(BigDecimal chequeamt) {
            this.chequeamt = chequeamt;
    }

    public BigDecimal getCredit() {
            return credit;
    }

    public void setCredit(BigDecimal credit) {
            this.credit = credit;
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

    public String getSaletype() {
            return saletype;
    }

    public void setSaletype(String saletype) {
            this.saletype = saletype;
    }

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }

    public BigDecimal getDsrdeduction() {
            return dsrdeduction;
    }

    public void setDsrdeduction(BigDecimal dsrdeduction) {
            this.dsrdeduction = dsrdeduction;
    }

    public BigDecimal getBalance() {
            return balance;
    }

    public void setBalance(BigDecimal balance) {
            this.balance = balance;
    }

    public String getChequedata() {
            return chequedata;
    }

    public void setChequedata(String chequedata) {
            this.chequedata = chequedata;
    }

    public Date getCheckcleardate() {
            return checkcleardate;
    }

    public void setCheckcleardate(Date checkcleardate) {
            this.checkcleardate = checkcleardate;
    }

    public Integer getCheckcollectevents() {
            return checkcollectevents;
    }

    public void setCheckcollectevents(Integer checkcollectevents) {
            this.checkcollectevents = checkcollectevents;
    }

    public Integer getHidden() {
            return hidden;
    }

    public void setHidden(Integer hidden) {
            this.hidden = hidden;
    }

    public Integer getIsInspected() {
            return is_inspected;
    }

    public void setIsInspected(Integer is_inspected) {
            this.is_inspected = is_inspected;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(customer_id.toString());
            values.add(customer_name);
            values.add(invno);
            values.add(ponumber);
            values.add(notes);
            values.add(payonly.toString());
            values.add(total.toString());
            values.add(cheque);
            values.add(chequedate.toString());
            values.add(date.toString());
            values.add(duedate.toString());
            values.add(datepaid.toString());
            values.add(terms_id.toString());
            values.add(salesman_id.toString());
            values.add(technician_id.toString());
            values.add(template_id.toString());
            values.add(cash.toString());
            values.add(chequeamt.toString());
            values.add(credit.toString());
            values.add(discrate);
            values.add(discamt.toString());
            values.add(saletype);
            values.add(status);
            values.add(dsrdeduction.toString());
            values.add(balance.toString());
            values.add(chequedata);
            values.add(checkcleardate.toString());
            values.add(checkcollectevents.toString());
            values.add(hidden.toString());
            values.add(is_inspected.toString());

            return values;
    }
    public void delete()
    {
            Invoice.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Invoice.insert(this);
            else
                    Invoice.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Invoice getByName(String name)
    {
            HashMap<Integer,Invoice> map=select(" name = '"+name+"'");
            for(Invoice item:map)return item;
            return null;
    }	
    */
    public static Invoice getById(Integer id) {
            ArrayList<Invoice> map=select(" id = '"+id.toString()+"'");
            for(Invoice item:map)return item;
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
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Invoice item)
    {
        delete(item.getId());
    }
    public static void insert(Invoice item)
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
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Invoice item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
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

    public static ArrayList<Invoice> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=MySqlDBHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            ArrayList<Invoice> items=new ArrayList<Invoice>();
            while (rs.next()) {
                items.add(new Invoice(rs));
                    //items.put(rs.getInt("id"), new Invoice(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(Invoice item,boolean withId)
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
    public static String implodeFieldsWithValues(Invoice item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Invoice:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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

        ArrayList<Invoice> items=Invoice.select("");
        for(Invoice item:items)
        {
            System.out.println(item);
        }
        System.out.println(Invoice.count(""));
    } 
}

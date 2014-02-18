/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterpriseclient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jaspertomas
 */
public class Test {
    public static void main(String args[])
    {
        boolean due=false,overdue=false;
        java.sql.Date date=new java.sql.Date(2014,02,11);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date today=new java.util.Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 15);
            java.util.Date duedate = cal.getTime();
            if(duedate.equals(today))due=true;
            else if(duedate.before(today))overdue=true;
            
            System.out.println(due);
            System.out.println(overdue);
    
    }
}

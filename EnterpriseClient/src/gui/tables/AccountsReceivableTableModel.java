/*
 * from http://www.javalobby.org/articles/jtable/?source=archives
 */
package gui.tables;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.query.AccountsReceivable;

 public class AccountsReceivableTableModel extends AbstractTableModel {

     protected static String[] columnNames = {
        "Date", "Customer", "Invoice", "Terms", "Amount", "Status", "TermsDays", "Due", "Overdue", "Amount"
    };     
     protected AccountsReceivable.RecordList data;

//     public InteractiveTableModel() {
//         dataVector = new ArrayList<Purchase>();
//     }
     public AccountsReceivableTableModel(AccountsReceivable.RecordList data, String[] columnNames) {
         this.data = data;
         this.columnNames=columnNames;
     }

     @Override
     public String getColumnName(int column) {
         return columnNames[column];
     }

     @Override
     public boolean isCellEditable(int row, int column) {
         return false;
//         if (column == HIDDEN_INDEX) return false;
//         else return true;
     }

     @Override
     public Object getValueAt(int row, int column) {
         AccountsReceivable record = (AccountsReceivable)data.get(row);
         switch (column) {
             case 0:
                return record.getDate();
             case 1:
                return record.getCustomer();
             case 2:
                return record.getInvoice();
             case 3:
                return record.getTerms();
             case 4:
                return record.getAmountstring();
             case 5:
                return record.getStatus();
             case 6:
                return record.getTermsdays();
             case 7:
                return record.getDue();
             case 8:
                return record.getOverdue();
             case 9:
                return record.getAmount();
             default:
                return new Object();
         }
     }

     public int getRowCount() {
         return data.size();
     }

     public int getColumnCount() {
         return columnNames.length;
     }


//    public static final Integer maxrecordsperpage=50;
//    private static Integer maxpages=0;
//
//    public static Integer getMaxpages() {
//        return maxpages;
//    }
    
    public static AccountsReceivableTableModel buildTableModel() {
        AccountsReceivable.RecordList data = new AccountsReceivable.RecordList();
        return new AccountsReceivableTableModel(data, columnNames);
    }    
//    public static AccountsReceivableTableModel buildTableModel(Integer page)
//            throws SQLException {
//        
//        String criteria=" status != 'Paid' order by invno ";
//        
////        if(false)//uncomment this and recompile to allow editing of FrmAccountsReceivable display
//        maxpages=Double.valueOf(Math.ceil(Double.valueOf(Invoice.count(criteria))/maxrecordsperpage)).intValue();
//        AccountsReceivable.RecordList data = new AccountsReceivable.RecordList();
//
////        if(false)//uncomment this and recompile to allow editing of FrmAccountsReceivable display
//        for(Invoice item:Invoice.select(criteria+" limit "+(maxrecordsperpage*page)+","+maxrecordsperpage)) {
//            data.add(new AccountsReceivable(item.getDate(),item.getCustomerId(),item.getInvno(),item.getTermsId(),item.getTotal(),item.getStatus()));
//            System.out.println(item.getInvno());
//        }
//        return new AccountsReceivableTableModel(data, columnNames);
//
//    }    

    public void setData(AccountsReceivable.RecordList data) {
        this.data = data;
        fireTableDataChanged();
    }
    

 }


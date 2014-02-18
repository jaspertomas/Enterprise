/*
 * from http://www.javalobby.org/articles/jtable/?source=archives
 */
package gui.tables;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.query.AccountsPayable;

 public class AccountsPayableTableModel extends AbstractTableModel {

     protected static String[] columnNames = {
        "Date", "Supplier", "Invoice", "Terms", "Amount", "Status", "TermsDays", "Due", "Overdue"
    };     
     protected AccountsPayable.RecordList data;

//     public InteractiveTableModel() {
//         dataVector = new ArrayList<Purchase>();
//     }
     public AccountsPayableTableModel(AccountsPayable.RecordList data, String[] columnNames) {
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
         AccountsPayable record = (AccountsPayable)data.get(row);
         switch (column) {
             case 0:
                return record.getDate();
             case 1:
                return record.getSupplier();
             case 2:
                return record.getInvoice();
             case 3:
                return record.getTerms();
             case 4:
                return record.getAmount();
             case 5:
                return record.getStatus();
             case 6:
                return record.getTermsdays();
             case 7:
                return record.getDue();
             case 8:
                return record.getOverdue();
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
    
    public static AccountsPayableTableModel buildTableModel() {
        AccountsPayable.RecordList data = new AccountsPayable.RecordList();
        return new AccountsPayableTableModel(data, columnNames);
    }    
//    public static AccountsPayableTableModel buildTableModel(Integer page)
//            throws SQLException {
//        
//        String criteria=" status != 'Paid' order by invno ";
//        
////        if(false)//uncomment this and recompile to allow editing of FrmAccountsPayable display
//        maxpages=Double.valueOf(Math.ceil(Double.valueOf(Invoice.count(criteria))/maxrecordsperpage)).intValue();
//        AccountsPayable.RecordList data = new AccountsPayable.RecordList();
//
////        if(false)//uncomment this and recompile to allow editing of FrmAccountsPayable display
//        for(Invoice item:Invoice.select(criteria+" limit "+(maxrecordsperpage*page)+","+maxrecordsperpage)) {
//            data.add(new AccountsPayable(item.getDate(),item.getSupplierId(),item.getInvno(),item.getTermsId(),item.getTotal(),item.getStatus()));
//            System.out.println(item.getInvno());
//        }
//        return new AccountsPayableTableModel(data, columnNames);
//
//    }    

    public void setData(AccountsPayable.RecordList data) {
        this.data = data;
        fireTableDataChanged();
    }
    

 }


/*
 * from http://www.javalobby.org/articles/jtable/?source=archives
 */
package gui.tables;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import models.Invoice;
import models.Purchase;

 public class AccountsReceivableTableModel extends AbstractTableModel {
     public static final int TITLE_INDEX = 0;
     public static final int ARTIST_INDEX = 1;
     public static final int ALBUM_INDEX = 2;
     public static final int HIDDEN_INDEX = 3;

     protected static String[] columnNames = {
        "Date", "Customer", "Invoice", "Terms", "Amount", "Status"
    };     
     protected ArrayList<AccountsReceivable> data;

//     public InteractiveTableModel() {
//         dataVector = new ArrayList<Purchase>();
//     }
     public AccountsReceivableTableModel(ArrayList<AccountsReceivable> data, String[] columnNames) {
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
                return record.getAmount();
             case 5:
                return record.getStatus();
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


    private static Integer maxrecordsperpage=50;
    public static AccountsReceivableTableModel buildTableModel()
            throws SQLException {

        return buildTableModel(0);
    }    
    public static AccountsReceivableTableModel buildTableModel(Integer page)
            throws SQLException {

        ArrayList<AccountsReceivable> data = new ArrayList<AccountsReceivable>();

//        if(false)//uncomment this and recompile to allow editing of FrmAccountsReceivable display
        for(Invoice item:Invoice.select(" 1 limit "+(maxrecordsperpage*page)+","+maxrecordsperpage).values()) {
            data.add(new AccountsReceivable(item.getDate(),item.getCustomerId(),item.getInvno(),item.getTermsId(),item.getTotal(),item.getStatus()));
        }
        return new AccountsReceivableTableModel(data, columnNames);

    }    
 }


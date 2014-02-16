/*
 * from http://www.javalobby.org/articles/jtable/?source=archives
 */
package gui.tables;

import java.sql.SQLException;
import java.util.ArrayList;
 import javax.swing.table.AbstractTableModel;
import models.Purchase;

 public class InteractiveTableModel extends AbstractTableModel {
     public static final int TITLE_INDEX = 0;
     public static final int ARTIST_INDEX = 1;
     public static final int ALBUM_INDEX = 2;
     public static final int HIDDEN_INDEX = 3;

     protected static String[] columnNames = {
        "Title", "Artist", "Album", ""
    };     
     protected ArrayList<Purchase> data;

//     public InteractiveTableModel() {
//         dataVector = new ArrayList<Purchase>();
//     }
     public InteractiveTableModel(ArrayList<Purchase> data, String[] columnNames) {
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
         Purchase record = (Purchase)data.get(row);
         switch (column) {
             case TITLE_INDEX:
                return record.getId();
             case ARTIST_INDEX:
                return record.getPono();
             case ALBUM_INDEX:
                return record.getInvno();
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



    public static InteractiveTableModel buildTableModel()
            throws SQLException {

        ArrayList<Purchase> data = new ArrayList<Purchase>();

        for(Purchase item:Purchase.select("").values()) {
            data.add(item);
        }
        return new InteractiveTableModel(data, columnNames);

    }    
 }
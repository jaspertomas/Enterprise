/*
 * from http://www.javalobby.org/articles/jtable/?source=archives
 */
package gui.tables;

import enterpriseclient.ClientProtocol;
import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import models.query.AccountsReceivable;


public class AccountsReceivableTable extends JPanel {


    protected JTable table;
    protected JScrollPane scroller;
    protected AccountsReceivableTableModel tableModel;
    

    public AccountsReceivableTable() {
        initComponent();
//        tableModel.addRows();
        tableModel.fireTableDataChanged();

        
    }
    

    public void initComponent() {
//        try {
//            table = new JTable();

            tableModel = AccountsReceivableTableModel.buildTableModel();
            maxpages=tableModel.getMaxpages();//this is calculated by buildTableModel
            
            table = new JTable(tableModel);
            //JOptionPane.showMessageDialog(null, new JScrollPane(table));
            //tableModel.addTableModelListener(new InteractiveTableModelListener(table));
            //table.setModel(tableModel);
            //table.setSurrendersFocusOnKeystroke(true);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
 

        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        TableColumn datecolumn = table.getColumnModel().getColumn(0);
        datecolumn.setPreferredWidth(75);
//        TableColumn amountcolumn = table.getColumnModel().getColumn(4);
//        amountcolumn.set
//        hidden.setMinWidth(2);
//        hidden.setPreferredWidth(2);
//        hidden.setMaxWidth(2);
//        hidden.setCellRenderer(new InteractiveRenderer(InteractiveTableModel.HIDDEN_INDEX));

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public void highlightLastRow(int row) {
        int lastrow = tableModel.getRowCount();
        if (row == lastrow - 1) {
            table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
        } else {
            table.setRowSelectionInterval(row + 1, row + 1);
        }

        table.setColumnSelectionInterval(0, 0);
    }

    class InteractiveRenderer extends DefaultTableCellRenderer {

        protected int interactiveColumn;

        public InteractiveRenderer(int interactiveColumn) {
            this.interactiveColumn = interactiveColumn;
        }

        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == interactiveColumn && hasFocus) {
                highlightLastRow(row);
            }

            return c;
        }
    }
    public void gotoPage(Integer page)
    {
        //...
        //formulate query
        //ask ClientProtocol to fetch data from server as JSON
        //complete with terms and customers
        //ClientProtocol receives data, calls this table via FrmAccountsReceivable singleton
        //and tells it to display the data received via another function
        String criteria=" status != 'Paid' ";
        criteria=criteria+" limit "+(tableModel.maxrecordsperpage*page)+","+tableModel.maxrecordsperpage;
        
//        for(Invoice item:Invoice.select(criteria+" limit "+(maxrecordsperpage*page)+","+maxrecordsperpage)) {
//            data.add(new AccountsReceivable(item.getDate(),item.getCustomerId(),item.getInvno(),item.getTermsId(),item.getTotal(),item.getStatus()));
//            System.out.println(item.getInvno());
//        }
        
        
        ClientProtocol.sendDbSelect("AccountsReceivable",criteria);
        
        
//        try {
//            tableModel=AccountsReceivableTableModel.buildTableModel(page);
//            maxpages=tableModel.getMaxpages();//this is calculated by buildTableModel
//            table.setModel(tableModel);
//            //tableModel.fireTableDataChanged();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }
    private Integer maxpages=0;
    public Integer getMaxPages()
    {
        return maxpages;
    }
    public void setData(AccountsReceivable.RecordList list)
    {
        tableModel.setData(list);
    }
    
}
class InteractiveTableModelListener implements TableModelListener {

    JTable table;

    InteractiveTableModelListener(JTable table) {
        this.table = table;
    }

    public void tableChanged(TableModelEvent evt) {
        if (evt.getType() == TableModelEvent.UPDATE) {
            int column = evt.getColumn();
            int row = evt.getFirstRow();
            System.out.println("row: " + row + " column: " + column);
            table.setColumnSelectionInterval(column+1 , column+1);
            table.setRowSelectionInterval(row, row);
        }
    }
}

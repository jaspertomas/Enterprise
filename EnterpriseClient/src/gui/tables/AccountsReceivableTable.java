/*
 * from http://www.javalobby.org/articles/jtable/?source=archives
 */
package gui.tables;

import enterpriseclient.ClientProtocol;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
        //tableModel.fireTableDataChanged();

        
    }
    

    public void initComponent() {
//        try {
//            table = new JTable();

        tableModel = AccountsReceivableTableModel.buildTableModel();
        table = new JTable(tableModel);

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
    public static final Integer maxrecordsperpage=50;
    private static Integer maxpages=0;
    private Integer page=0;

    public Integer getPage() {
        return page;
    }
    
    public void gotoPage(Integer page)
    {
        this.page=page;
        //...
        //formulate query
        //ask ClientProtocol to fetch data from server as JSON
        //complete with terms and customers
        //ClientProtocol receives data, calls this table via FrmAccountsReceivable singleton
        //and tells it to display the data received via another function
        String criteria=" status != 'Paid' ";
        criteria=criteria+" limit "+(maxrecordsperpage*page)+","+maxrecordsperpage;
        ClientProtocol.sendDbSelect("AccountsReceivable",criteria);
    }
    public Integer getMaxPages()
    {
        return maxpages;
    }
    public void setData(AccountsReceivable.RecordList list,Integer count)
    {
        tableModel.setData(list);
        System.out.println("count is "+count);
        maxpages=Double.valueOf(Math.ceil(Double.valueOf(count)/maxrecordsperpage)).intValue();
        tableModel.setRowColour(1, Color.YELLOW);
        tableModel.fireTableDataChanged();
    }
}
//class InteractiveTableModelListener implements TableModelListener {
//
//    JTable table;
//
//    InteractiveTableModelListener(JTable table) {
//        this.table = table;
//    }
//
//    public void tableChanged(TableModelEvent evt) {
//        if (evt.getType() == TableModelEvent.UPDATE) {
//            int column = evt.getColumn();
//            int row = evt.getFirstRow();
//            System.out.println("row: " + row + " column: " + column);
//            table.setColumnSelectionInterval(column+1 , column+1);
//            table.setRowSelectionInterval(row, row);
//        }
//    }
//}

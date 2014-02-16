/*
 * from http://www.javalobby.org/articles/jtable/?source=archives
 */
package gui.tables;

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


public class InteractiveForm extends JPanel {


    protected JTable table;
    protected JScrollPane scroller;
    protected InteractiveTableModel tableModel;

    public InteractiveForm() {
        initComponent();
//        tableModel.addRows();
        tableModel.fireTableDataChanged();

        
    }
    

    public void initComponent() {
        try {
//            table = new JTable();

            tableModel = InteractiveTableModel.buildTableModel();
            table = new JTable(tableModel);
            //JOptionPane.showMessageDialog(null, new JScrollPane(table));
            //tableModel.addTableModelListener(new InteractiveTableModelListener(table));
            //table.setModel(tableModel);
            //table.setSurrendersFocusOnKeystroke(true);
        } catch (SQLException ex) {
            Logger.getLogger(InteractiveForm.class.getName()).log(Level.SEVERE, null, ex);
        }
 

        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
//        TableColumn hidden = table.getColumnModel().getColumn(InteractiveTableModel.HIDDEN_INDEX);
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

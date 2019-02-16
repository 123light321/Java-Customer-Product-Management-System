import sun.util.calendar.BaseCalendar;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shahrose on 5/24/2017.
// */
public class InvoiceCRUD extends JFrame {
    private final JPanel buttonPanel,createInvoicePanel,showInvoicePanel,historyPanel,comboBoxPanel;
    private final JButton createButton , retrieveButton, deleteButton, backButton;
    private final JComboBox customerComboBox, productComboBox;
    protected static ArrayList<Invoice> invoices = new ArrayList<>();
    private final JTable invoiceTable;
    private  JTable invoiceDetailTable;
    private static DefaultTableModel tableModel2;
    private static DefaultTableModel tableModel;
    private Integer id =1 ;
    private static JList invoiceList;
    private static String[] invoiceListId;
    private int total;

    public  InvoiceCRUD(){
        super("INVOICE-CRUD");
        setListIds();
        setId();


        setLayout(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout());
        add(buttonPanel,BorderLayout.SOUTH);

        createInvoicePanel = new JPanel(new FlowLayout());
        add(createInvoicePanel,BorderLayout.WEST);

        historyPanel = new JPanel(new BorderLayout());
        add(historyPanel,BorderLayout.CENTER);

        showInvoicePanel = new JPanel(new BorderLayout());
        add(showInvoicePanel,BorderLayout.EAST);

        comboBoxPanel = new JPanel(new FlowLayout(0));
        add(comboBoxPanel,BorderLayout.NORTH);

        customerComboBox =new JComboBox(CustomerCRUD.idNamesArray);
        comboBoxPanel.add(customerComboBox);

        productComboBox =new JComboBox(ProductCRUD.idNamesArray);
        comboBoxPanel.add(productComboBox);

        invoiceList = new JList();
        invoiceList.setVisibleRowCount(10);
        invoiceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //add(new JScrollPane(invoiceList), BorderLayout.EAST);

        tableModel = new DefaultTableModel();
        addColumnTable();
        invoiceTable = new JTable(tableModel);
        setTableValues();
        invoiceTable.setFillsViewportHeight(true);
        invoiceTable.setEnabled(true);
        invoiceTable.setPreferredSize(new Dimension(120,100));
        createInvoicePanel.add(new JScrollPane(invoiceTable), BorderLayout.WEST);

        tableModel2 = new DefaultTableModel();
        addColumnTable2();
        invoiceDetailTable = new JTable(tableModel2);
        invoiceDetailTable.setFillsViewportHeight(true);
        invoiceDetailTable.setEnabled(true);
        invoiceDetailTable.setPreferredSize(new Dimension(120,100));
        createInvoicePanel.add(new JScrollPane(invoiceDetailTable), BorderLayout.CENTER);

        backButton = new JButton("BACK");
        buttonPanel.add(backButton);

        retrieveButton = new JButton("RETRIEVE");
        buttonPanel.add(retrieveButton);

        deleteButton = new JButton("DELETE");
        buttonPanel.add(deleteButton);

        createButton = new JButton("SUBMIT");
        buttonPanel.add(createButton);

        MenuOptionButtons menuOptionButtonsHandler = new MenuOptionButtons();
        createButton.addActionListener(menuOptionButtonsHandler);
        deleteButton.addActionListener(menuOptionButtonsHandler);
        retrieveButton.addActionListener(menuOptionButtonsHandler);
        backButton.addActionListener(menuOptionButtonsHandler);
        
        designOfComponents();

    }
    public  void designOfComponents(){
        invoiceTable.setPreferredSize(new Dimension(200,200));
        invoiceDetailTable.setPreferredSize(new Dimension(200,200));
        buttonPanel.setBackground(Color.BLACK);
        comboBoxPanel.setBackground(Color.BLACK);
        Icon createIcon = new ImageIcon((getClass().getResource("submit.png")));
        createButton.setIcon(createIcon);
        createButton.setPreferredSize(new Dimension(150,70));

        Icon deleteIcon = new ImageIcon((getClass().getResource("delete.png")));
        deleteButton.setIcon(deleteIcon);
        deleteButton.setPreferredSize(new Dimension(150,70));

        Icon retrieveIcon = new ImageIcon((getClass().getResource("retrieve.png")));
        retrieveButton.setIcon(retrieveIcon);
        retrieveButton.setPreferredSize(new Dimension(160,70));

        Icon backIcon = new ImageIcon((getClass().getResource("go_back.png")));
        backButton.setIcon(backIcon);
        backButton.setPreferredSize(new Dimension(140,70));
    }
    public void setTableValues(){
        Object[] data = new Object[4];
        tableModel.setRowCount(0);
        for (int i = 0; i < invoices.size(); i++) {
            data[0] = invoices.get(i).invoiceId;
            data[1] = invoices.get(i).customerId;
            data[2] = invoices.get(i).date;
            data[3] = invoices.get(i).total;
            tableModel.addRow(data);
        }
    }
    public void addColumnTable(){
        tableModel.addColumn("Invoice-ID");
        tableModel.addColumn("Customer-ID");
        tableModel.addColumn("Date");
        tableModel.addColumn("Total");

    }
    private class MenuOptionButtons implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()== createButton){

                invoices.add(getValuesForInvoice());
                DatabaseInvoice.addInvoiceRecordsToFile();
                id++;

                JOptionPane.showMessageDialog(null,"The Invoice has been added to DatabaseInvoice");
                setListIds();
                setTableValues();
            }
            else if(e.getSource()==deleteButton){
                if(!(invoiceTable.getSelectedRow() == -1)) {
                    System.out.println(invoiceTable.getSelectedRow());
                    invoices.remove(invoiceTable.getSelectedRow());
                    DatabaseInvoice.addInvoiceRecordsToFile();
                    setListIds();
                    setTableValues();
                }else {
                    JOptionPane.showMessageDialog(null,"Choose ID from List to Delete");
                }
            }
            else if(e.getSource()==retrieveButton){

                    retrieveTableRows();
                    setTableValues();
            }

            else if(e.getSource() == backButton){
                Menu.invoiceCRUD.setVisible(false);
                PointOfSaleSystemTest.menu.setVisible(true);
            }
        }
    }

    public Invoice getValuesForInvoice(){
        int customerId = CustomerCRUD.customers.get(customerComboBox.getSelectedIndex()).id;
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();
        int total2 = total;
        Invoice invoice;
        invoice = new Invoice( id, customerId, df.format(date) , total2);
        total=0;
        return invoice;
    }
    public void retrieveTableRows(){
        int num=-1;
        try {
            num = productComboBox.getSelectedIndex();
            tableModel2.addRow(getRowAt(num));
        }catch (Exception e){
            System.out.println("There is not product in list");
        }

    }
    public Object[] getRowAt(int row) {
        Object[] result = new Object[5];

        for (int i = 0; i < 5; i++) {
            result[i] = ProductCRUD.productTable.getModel().getValueAt(row, i);
        }
        int a = Integer.parseInt(result[3].toString());
        int b = Integer.parseInt(result[2].toString());
        total=a*b;
        return result;
    }

    public void addColumnTable2(){
        tableModel2.addColumn("ID");
        tableModel2.addColumn("NAME");
        tableModel2.addColumn("UNIT");
        tableModel2.addColumn("UNIT-PRICE");
        tableModel2.addColumn("QUANTITY-IN-STOCK");
    }
    public void setListIds(){
        invoiceListId = new String [invoices.size()];
        try {
            for (int i = 0; i < invoices.size(); i++) {
                invoiceListId[i] = String.format("  " + invoices.get(i).invoiceId + " --- " + CustomerCRUD.customers.get(invoices.get(i).customerId).name);
            }
        }catch (Exception e){
            System.out.println("There is no invoices");
        }
        //invoiceList.setListData(invoiceListId);
    }
    public void setId(){
        try{
            id = (invoices.get(invoices.size()-1).invoiceId+1);
        }catch (IndexOutOfBoundsException e){
            System.out.println("This is first Run so File is Empty");
        }
    }
}

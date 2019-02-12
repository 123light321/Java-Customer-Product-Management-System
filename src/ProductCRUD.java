import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by shahrose on 5/24/2017.
 */
public class ProductCRUD extends JFrame{
    private final JLabel idLabel, nameLabel, unitLabel, unitPriceLabel, quantityInStockLabel;
    private final JButton createButton , retrieveButton, updateButton, deleteButton, listButton, backButton;
    private final JTextField idField, nameField, unitField, unitPriceField, quantityInStockField;
    private final JPanel panelMenu, panel1, panel2, panel3, panel4, panel5 , panel6, buttonPanel1, buttonPanel2;
    protected static JTable productTable;
    private final JList productList;
    protected static ArrayList<Product> products = new ArrayList<>();
    private Integer id =1 ;
    private int counter;
    protected static String[] idNamesArray;
    private static DefaultTableModel tableModel;

    public ProductCRUD() {
        super("ProductCRUD");
        setId();

        panelMenu = new JPanel(new BorderLayout());
        getContentPane().add(panelMenu);

        panel1 = new JPanel(new BorderLayout());
        panelMenu.add(panel1, BorderLayout.CENTER);

        panel2 = new JPanel(new BorderLayout());
        panelMenu.add(panel2, BorderLayout.EAST);

        buttonPanel1 = new JPanel(new FlowLayout());
        panel1.add(buttonPanel1, BorderLayout.SOUTH);

        buttonPanel2 = new JPanel(new FlowLayout());
        panel2.add(buttonPanel2, BorderLayout.SOUTH);

        productList = new JList();
        setListIds();
        productList.setVisibleRowCount(10);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel1.add(new JScrollPane(productList), BorderLayout.CENTER);

        panel6 = new JPanel();
        panel1.add(panel6,BorderLayout.WEST);

        tableModel = new DefaultTableModel();
        addColumnTable();
        productTable = new JTable(tableModel);
        setTableValues();
        productTable.setFillsViewportHeight(true);
        productTable.setEnabled(false);
        productTable.setPreferredSize(new Dimension(120,100));
        panel6.add(new JScrollPane(productTable), BorderLayout.WEST);

        panel5 = new JPanel(new BorderLayout(0,10));
        panel2.add(panel5,BorderLayout.CENTER);

        panel3 = new JPanel(new GridLayout(5, 2,-85,20));
        panel5.add(panel3, BorderLayout.CENTER);

        panel4 = new JPanel(new FlowLayout(0));
        panel4.setPreferredSize(new Dimension(100,80));
        panel5.add(panel4, BorderLayout.SOUTH);

        idLabel = new JLabel(" The ID is : ");
        panel3.add(idLabel);
        idField = new JTextField(id.toString(), 3);
        panel3.add(idField);

        nameLabel = new JLabel(" Enter Name : ");
        panel3.add(nameLabel);
        nameField = new JTextField();
        panel3.add(nameField);

        unitLabel = new JLabel(" Enter Unit : ");
        panel3.add(unitLabel);
        unitField = new JTextField();
        panel3.add(unitField);

        unitPriceLabel = new JLabel(" Enter Unit Price : ");
        panel3.add(unitPriceLabel);
        unitPriceField = new JTextField();
        panel3.add(unitPriceField);

        quantityInStockLabel = new JLabel(" Enter the Quantity in Stock : ");
        panel3.add(quantityInStockLabel);
        quantityInStockField = new JTextField();
        panel3.add(quantityInStockField);

        backButton = new JButton("BACK");
        buttonPanel1.add(backButton);

        retrieveButton = new JButton("RETRIEVE");
        buttonPanel1.add(retrieveButton);

        deleteButton = new JButton("DELETE");
        buttonPanel1.add(deleteButton);

        listButton = new JButton("LIST");
        buttonPanel1.add(listButton);

        updateButton =new JButton("UPDATE");
        buttonPanel2.add(updateButton);

        createButton = new JButton("SUBMIT");
        buttonPanel2.add(createButton);


        //Listener/Handler Component Binding
        MenuOptionButtons menuOptionButtonsHandler = new MenuOptionButtons();
        createButton.addActionListener(menuOptionButtonsHandler);
        deleteButton.addActionListener(menuOptionButtonsHandler);
        retrieveButton.addActionListener(menuOptionButtonsHandler);
        updateButton.addActionListener(menuOptionButtonsHandler);
        listButton.addActionListener(menuOptionButtonsHandler);
        backButton.addActionListener(menuOptionButtonsHandler);


        designOfComponents();
    }
    public void designOfComponents(){
        panelMenu.setBackground(Color.BLACK);
        panel1.setBackground(Color.BLACK);
        panel2.setBackground(Color.BLUE);
        panel3.setBackground(Color.GREEN);
        panel4.setBackground(Color.GREEN);
        panel5.setBackground(Color.GREEN);
        buttonPanel1.setBackground(Color.PINK);
        buttonPanel2.setBackground(Color.PINK);

        idField.setEditable(false);
        productList.setPreferredSize(new Dimension(120,10));
        deleteButton.setToolTipText("Select a ID from List to remove from History");

        idField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        unitField.setHorizontalAlignment(SwingConstants.CENTER);
        unitPriceField.setHorizontalAlignment(SwingConstants.CENTER);
        quantityInStockField.setHorizontalAlignment(SwingConstants.CENTER);

        Font font1 = new Font(null, Font.PLAIN & Font.BOLD,13);
        Font font2 = new Font(null, Font.ROMAN_BASELINE  ,14);

        idLabel.setFont(font1);
        nameLabel.setFont(font1);
        unitLabel.setFont(font1);
        quantityInStockLabel.setFont(font1);
        unitPriceLabel.setFont(font1);
        productList.setFont(font1);

        idField.setFont(font2);
        nameField.setFont(font2);
        unitField.setFont(font2);
        unitPriceField.setFont(font2);
        quantityInStockField.setFont(font2);

        Icon createIcon = new ImageIcon((getClass().getResource("submit.png")));
        createButton.setIcon(createIcon);
        createButton.setPreferredSize(new Dimension(150,70));

        Icon deleteIcon = new ImageIcon((getClass().getResource("delete.png")));
        deleteButton.setIcon(deleteIcon);
        deleteButton.setPreferredSize(new Dimension(150,70));

        Icon retrieveIcon = new ImageIcon((getClass().getResource("retrieve.png")));
        retrieveButton.setIcon(retrieveIcon);
        retrieveButton.setPreferredSize(new Dimension(160,70));

        Icon listIcon = new ImageIcon((getClass().getResource("list.jpg")));
        listButton.setIcon(listIcon);
        listButton.setPreferredSize(new Dimension(145,70));

        Icon updateIcon = new ImageIcon((getClass().getResource("update.png")));
        updateButton.setIcon(updateIcon);
        updateButton.setPreferredSize(new Dimension(160,70));

        Icon backIcon = new ImageIcon((getClass().getResource("go_back.png")));
        backButton.setIcon(backIcon);
        backButton.setPreferredSize(new Dimension(140,70));
    }

    private class MenuOptionButtons implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()== createButton){

                products.add(getValuesForProduct());
                DatabaseProduct.addProductRecordsToFile();

                id++;
                fillEmptyAllTextFields();

                JOptionPane.showMessageDialog(null,"The Product has been added to DatabaseProduct");
                setListIds();
                setTableValues();
            }
            else if(e.getSource()==deleteButton){
                if(!(productList.getSelectedIndex()== -1)) {
                    products.remove(productList.getSelectedIndex());
                    DatabaseProduct.addProductRecordsToFile();
                    setListIds();
                    setId();
                    idField.setText(id.toString());
                }else {
                    JOptionPane.showMessageDialog(null,"Choose ID from List to Delete");
                }

            }
            else if(e.getSource()==retrieveButton){
                if(!(productList.getSelectedIndex()== -1)) {
                    retrieveProduct();
                }else {
                    JOptionPane.showMessageDialog(null,"Choose ID from List to Delete");
                }
            }
            else if (e.getSource() == updateButton){
                setEditable();
                int a = Integer.parseInt(idField.getText());
                checkId(a);
                products.set( counter , getValuesForProduct());
                setListIds();
            }
            else if(e.getSource()== listButton){
                setTableValues();
            }
            else if(e.getSource() == backButton){
                Menu.productCRUD.setVisible(false);
                PointOfSaleSystemTest.menu.setVisible(true);
            }
        }
    }
    public void setListIds(){
        idNamesArray = new String [products.size()];
        for(int i = 0; i< products.size(); i++) {
            idNamesArray [i]= String.format("  " + products.get(i).id + " --- " + products.get(i).name );
        }
        productList.setListData(idNamesArray);
    }
    public  void retrieveProduct() {
        setUnEditable();
        idField.setText(String.format(products.get(productList.getSelectedIndex()).id+""));
        nameField.setText(products.get(productList.getSelectedIndex()).name);
        unitField.setText(products.get(productList.getSelectedIndex()).unit+"");
        unitPriceField.setText(products.get(productList.getSelectedIndex()).unitPrice+"");
        quantityInStockField.setText(products.get(productList.getSelectedIndex()).quantityInStock+"");

    }
    public void setUnEditable(){
        idField.setEditable(false);
        nameField.setEditable(false);
        unitField.setEditable(false);
        unitPriceField.setEditable(false);
        quantityInStockField.setEditable(false);
        createButton.setEnabled(false);
    }
    public void setEditable(){

        idField.setEditable(false);
        nameField.setEditable(true);
        unitField.setEditable(true);
        unitPriceField.setEditable(true);
        quantityInStockField.setEditable(true);
        createButton.setEnabled(true);
    }

    public void setId(){
        try{
            id = (products.get(products.size()-1).id+1);
        }catch (IndexOutOfBoundsException e){
            System.out.println("This is first Run so File is Empty");
        }
    }

    public void checkId(int c){
        for( int i=0 ; i < products.size() ; ){
            if(c == products.get(i).id){
                counter=i;
                break;
            }
            i++;
        }
        System.out.println(counter);
    }

    public void setTableValues() {
        Object[] data = new Object[5];
        tableModel.setRowCount(0);
        for (int i = 0; i < products.size(); i++) {
            data[0] = products.get(i).id;
            data[1] = products.get(i).name;
            data[2] = products.get(i).unit;
            data[3] = products.get(i).unitPrice;
            data[4] = products.get(i).quantityInStock;
            tableModel.addRow(data);
        }
    }
    public void addColumnTable(){
        tableModel.addColumn("ID");
        tableModel.addColumn("NAME");
        tableModel.addColumn("UNIT");
        tableModel.addColumn("UNIT-PRICE");
        tableModel.addColumn("QUANTITY-IN-STOCK");
    }

    public Product getValuesForProduct(){

        int id1 = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int unit = Integer.parseInt(unitField.getText());
        int unitPrice = Integer.parseInt(unitPriceField.getText());
        int quantityInStock = Integer.parseInt(quantityInStockField.getText());
        Product product;
        if(!(id==id1)) {
            product = new Product(id1, name, unit, unitPrice , quantityInStock);
        }else{
            product = new Product(id, name, unit, unitPrice , quantityInStock);
        }
        return product;
    }
    public void fillEmptyAllTextFields(){
        idField.setText(id.toString());
        nameField.setText("");
        unitField.setText("");
        unitPriceField.setText("");
        quantityInStockField.setText("");
    }
}

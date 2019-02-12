import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by shahrose on 5/23/2017.
 */
public class CustomerCRUD extends JFrame{
    private final JLabel idLabel, nameLabel, emailLabel, genderLabel,cityLabel, cellNumberLable;
    private final JButton createButton , retrieveButton, updateButton, deleteButton, listButton, backButton;
    private final JTextField idField, nameField, emailField, cityField, cellNumberField;
    private final JRadioButton maleGenderRadioButton, femaleGenderRadioButton;
    private final JPanel panelMenu, panel1, panel2, panel3, panel4, panel5 , panel6, buttonPanel1, buttonPanel2;
    private final ButtonGroup genderButtonGroup;
    private JTable customerTable;
    private final JList customersList;
    protected static ArrayList<Customer> customers = new ArrayList<>();
    private Integer id =1 ;
    private int counter;
    protected static String[] idNamesArray;
    private static DefaultTableModel tableModel;

    public CustomerCRUD() {
        super("CustomerCRUD");
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

        customersList = new JList();
        setListIds();
        customersList.setVisibleRowCount(10);
        customersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel1.add(new JScrollPane(customersList), BorderLayout.CENTER);

        panel6 = new JPanel();
        panel1.add(panel6,BorderLayout.WEST);

        tableModel = new DefaultTableModel();
        addColumnTable();
        customerTable = new JTable(tableModel);
        setTableValues();
        customerTable.setFillsViewportHeight(true);
        customerTable.setEnabled(false);
        customerTable.setPreferredSize(new Dimension(120,100));
        panel6.add(new JScrollPane(customerTable), BorderLayout.WEST);

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

        emailLabel = new JLabel(" Enter Email : ");
        panel3.add(emailLabel);
        emailField = new JTextField();
        panel3.add(emailField);

        cityLabel = new JLabel(" Enter City : ");
        panel3.add(cityLabel);
        cityField = new JTextField();
        panel3.add(cityField);

        cellNumberLable = new JLabel(" Enter Cell-Number : ");
        panel3.add(cellNumberLable);
        cellNumberField = new JTextField();
        panel3.add(cellNumberField);

        genderLabel = new JLabel("Choose Gender : ");
        panel4.add(genderLabel);
        maleGenderRadioButton = new JRadioButton("MALE");
        panel4.add(maleGenderRadioButton);
        femaleGenderRadioButton = new JRadioButton("FEMALE");
        panel4.add(femaleGenderRadioButton);

        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleGenderRadioButton);
        genderButtonGroup.add(femaleGenderRadioButton);

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

        maleGenderRadioButton.setBackground(Color.GREEN);
        femaleGenderRadioButton.setBackground(Color.GREEN);

        idField.setEditable(false);
        customersList.setPreferredSize(new Dimension(120,10));
        deleteButton.setToolTipText("Select a ID from List to remove from History");

        idField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        emailField.setHorizontalAlignment(SwingConstants.CENTER);
        cityField.setHorizontalAlignment(SwingConstants.CENTER);
        cellNumberField.setHorizontalAlignment(SwingConstants.CENTER);

        Font font1 = new Font(null, Font.PLAIN & Font.BOLD,13);
        Font font2 = new Font(null, Font.ROMAN_BASELINE  ,14);

        idLabel.setFont(font1);
        nameLabel.setFont(font1);
        emailLabel.setFont(font1);
        cityLabel.setFont(font1);
        cellNumberLable.setFont(font1);
        genderLabel.setFont(font1);
        maleGenderRadioButton.setFont(font1);
        femaleGenderRadioButton.setFont(font2);
        customersList.setFont(font1);

        idField.setFont(font2);
        nameField.setFont(font2);
        emailField.setFont(font2);
        cityField.setFont(font2);
        cellNumberField.setFont(font2);

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

                customers.add(getValuesForCustomer());
                DatabaseCustomer.addCustomerRecordsToFile();

                id++;
                fillEmptyAllTextFields();

                JOptionPane.showMessageDialog(null,"The Customer has been added to DatabaseCustomer");
                setListIds();
                setTableValues();
            }
            else if(e.getSource()==deleteButton){
                if(!(customersList.getSelectedIndex()== -1)) {
                    customers.remove(customersList.getSelectedIndex());
                    DatabaseCustomer.addCustomerRecordsToFile();
                    setListIds();
                    setId();
                    idField.setText(id.toString());
                }else {
                    JOptionPane.showMessageDialog(null,"Choose ID from List to Delete");
                }

            }
            else if(e.getSource()==retrieveButton){
                if(!(customersList.getSelectedIndex()== -1)) {
                    retrieveCustomer();
                }else {
                    JOptionPane.showMessageDialog(null,"Choose ID from List to Delete");
                }
            }
            else if (e.getSource() == updateButton){
                setEditable();
                int a = Integer.parseInt(idField.getText());
                checkId(a);
                customers.set( counter , getValuesForCustomer());
                setListIds();
            }
            else if(e.getSource()== listButton){
                setTableValues();
            }
            else if(e.getSource() == backButton){
                Menu.customerCRUD.setVisible(false);
                PointOfSaleSystemTest.menu.setVisible(true);
            }
        }
    }
    public void setListIds(){
        idNamesArray = new String [customers.size()];
        for(int i=0; i<customers.size();i++) {
            idNamesArray [i]= String.format("  " + customers.get(i).id + " --- " + customers.get(i).name );
        }
        customersList.setListData(idNamesArray);
    }
    public  void retrieveCustomer() {
        setUnEditable();
        idField.setText(String.format(customers.get(customersList.getSelectedIndex()).id+""));
        nameField.setText(customers.get(customersList.getSelectedIndex()).name);
        emailField.setText(customers.get(customersList.getSelectedIndex()).email);
        cityField.setText(customers.get(customersList.getSelectedIndex()).city);
        cellNumberField.setText(customers.get(customersList.getSelectedIndex()).cellNumber);
        if (customers.get(customersList.getSelectedIndex()).gender) {
            genderButtonGroup.setSelected(maleGenderRadioButton.getModel(), true);
        }else {
            genderButtonGroup.setSelected(femaleGenderRadioButton.getModel(), true);
        }
    }
    public void setUnEditable(){
        idField.setEditable(false);
        nameField.setEditable(false);
        emailField.setEditable(false);
        cityField.setEditable(false);
        cellNumberField.setEditable(false);
        maleGenderRadioButton.setEnabled(false);
        femaleGenderRadioButton.setEnabled(false);
        createButton.setEnabled(false);
    }
    public void setEditable(){

        idField.setEditable(false);
        nameField.setEditable(true);
        emailField.setEditable(true);
        cityField.setEditable(true);
        cellNumberField.setEditable(true);
        maleGenderRadioButton.setEnabled(true);
        femaleGenderRadioButton.setEnabled(true);
        createButton.setEnabled(true);
    }

    public void setId(){
        try{
            id = (customers.get(customers.size()-1).id+1);
        }catch (IndexOutOfBoundsException e){
            System.out.println("This is first Run so File is Empty");
        }
    }

    public void checkId(int c){
        for( int i=0 ; i < customers.size() ; ){
            if(c == customers.get(i).id){
                counter=i;
                break;
            }
            i++;
        }
        System.out.println(counter);
    }

    public void setTableValues() {
        Object[] data = new Object[7];
        tableModel.setRowCount(0);
        for (int i = 0; i < customers.size(); i++) {
            data[0] = customers.get(i).id;
            data[1] = customers.get(i).name;
            data[2] = customers.get(i).email;
            data[3] = customers.get(i).genderToString();
            data[4] = customers.get(i).city;
            data[5] = customers.get(i).cellNumber;
            data[6] = customers.get(i).activeToString();
            tableModel.addRow(data);
        }
    }
    public void addColumnTable(){
        tableModel.addColumn("ID");
        tableModel.addColumn("NAME");
        tableModel.addColumn("EMAIL");
        tableModel.addColumn("GENDER");
        tableModel.addColumn("CITY");
        tableModel.addColumn("CELL-NUMBER");
        tableModel.addColumn("ACCOUNT");
    }

    public Customer getValuesForCustomer(){
        int id1 = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String email = emailField.getText();
        boolean gender = maleGenderRadioButton.isSelected();
        String city = cityField.getText();
        String cellNumber = cellNumberField.getText();
        Customer customer;
        if(!(id==id1)) {
            customer = new Customer(id1, name, email, gender, true, city, cellNumber);
        }else{
            customer = new Customer(id, name, email, gender, true, city, cellNumber);
        }
        return customer;
    }
    public void fillEmptyAllTextFields(){
        idField.setText(id.toString());
        nameField.setText("");
        emailField.setText("");
        genderButtonGroup.clearSelection();
        cityField.setText("");
        cellNumberField.setText("");
    }
}

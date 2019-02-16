import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shahrose on 5/25/2017.
 */
public class Menu extends JFrame {
    private final JButton customerButton, productButton, invoiceButton;
    protected static CustomerCRUD customerCRUD;
    protected static ProductCRUD productCRUD;
    protected static InvoiceCRUD invoiceCRUD;

    public  Menu(){
        super("MENU");
        setLayout(new GridLayout(3,1,0,80));
        getContentPane().setBackground(Color.BLUE);

        customerButton = new JButton("CUSTOMER-CRUD");
        add(customerButton);

        productButton = new JButton("PRODUCT-CRUD");
        add(productButton);

        invoiceButton = new JButton("INVOICE-CRUD");
        add(invoiceButton);

        MenuHandler handler= new MenuHandler();
        customerButton.addActionListener(handler);
        productButton.addActionListener(handler);
        invoiceButton.addActionListener(handler);

    }
    private class MenuHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==customerButton){
                customerCRUD = new CustomerCRUD();
                customerCRUD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                customerCRUD.setSize(950,500);
                customerCRUD.setLocationRelativeTo(null);
                customerCRUD.setResizable(false);
                customerCRUD.setVisible(true);
                productCRUD.setVisible(false);
                invoiceCRUD.setVisible(false);
                PointOfSaleSystemTest.menu.setVisible(false);
            }
            else if (e.getSource()==productButton){
                productCRUD = new ProductCRUD();
                productCRUD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                productCRUD.setSize(950,500);
                productCRUD.setLocationRelativeTo(null);
                productCRUD.setResizable(false);
                productCRUD.setVisible(true);
                customerCRUD.setVisible(false);
                invoiceCRUD.setVisible(false);
                PointOfSaleSystemTest.menu.setVisible(false);
            }
            else if(e.getSource()==invoiceButton){
                customerCRUD = new CustomerCRUD();
                productCRUD = new ProductCRUD();
                invoiceCRUD = new InvoiceCRUD();
                invoiceCRUD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                invoiceCRUD.setSize(950,500);
                invoiceCRUD.setLocationRelativeTo(null);
                invoiceCRUD.setResizable(false);
                invoiceCRUD.setVisible(true);
                productCRUD.setVisible(false);
                customerCRUD.setVisible(false);
                PointOfSaleSystemTest.menu.setVisible(false);
            }
        }
    }
}

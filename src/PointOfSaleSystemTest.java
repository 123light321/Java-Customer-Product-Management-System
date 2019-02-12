/**
 * Created by shahrose on 5/23/2017.
 */
import javax.swing.*;

public class PointOfSaleSystemTest {
    static Menu menu;
    public static  void main (String [] args){
        DatabaseCustomer.readCustomerRecordsFromFile();
        DatabaseProduct.readProductRecordsFromFile();
        DatabaseInvoice.readInvoiceRecordsFromFile();

        menu  =new Menu();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(350,500);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setVisible(true);
    }
}

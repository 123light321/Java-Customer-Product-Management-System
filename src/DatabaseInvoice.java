import java.io.*;

/**
 * Created by shahrose on 5/24/2017.
 */
public class DatabaseInvoice {
    public static void addInvoiceRecordsToFile() {
        try {
            FileOutputStream file = new FileOutputStream("Assignment3.bin");
            ObjectOutputStream object = new ObjectOutputStream(file);

            for (Invoice invoice : InvoiceCRUD.invoices) {
                object.writeObject(invoice);
            }
            object.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void readInvoiceRecordsFromFile() {
        try {
            FileInputStream file = new FileInputStream("Assignment3.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            while (true ){
                Invoice invoice = (Invoice) object.readObject();
                InvoiceCRUD.invoices.add(invoice);
                if(invoice==null){
                    break;
                }
            }
            object.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch ( EOFException e){
            System.out.println("");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}


/**
 * Created by shahrose on 5/23/2017.
 */
import java.io.*;
public class DatabaseCustomer {
    public static void addCustomerRecordsToFile() {
        try {
            FileOutputStream file = new FileOutputStream("Assignment.bin");
            ObjectOutputStream object = new ObjectOutputStream(file);

            for (Customer customer : CustomerCRUD.customers) {
                object.writeObject(customer);
            }
            object.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void readCustomerRecordsFromFile() {
        try {
            FileInputStream file = new FileInputStream("Assignment.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            while (true ){
                Customer customer = (Customer) object.readObject();
                CustomerCRUD.customers.add(customer);
                if(customer==null){
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

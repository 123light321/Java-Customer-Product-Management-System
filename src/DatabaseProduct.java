import java.io.*;

/**
 * Created by shahrose on 5/24/2017.
 */
public class DatabaseProduct {
    public static void addProductRecordsToFile() {
        try {
            FileOutputStream file = new FileOutputStream("Assignment2.bin");
            ObjectOutputStream object = new ObjectOutputStream(file);

            for (Product product : ProductCRUD.products) {
                object.writeObject(product);
            }
            object.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void readProductRecordsFromFile() {
        try {
            FileInputStream file = new FileInputStream("Assignment2.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            while (true ){
                Product product = (Product) object.readObject();
                ProductCRUD.products.add(product);
                if(product==null){
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

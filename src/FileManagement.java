import java.io.*;

/*This file contains all the methods to save and retieve data from the binary file for
Customers, Products and Invoices. All these methods cannot be called outside this package.*/

class DatabaseCustomer {
    static void addCustomerRecordsToFile() {
        try {
            //setting the output file name
            FileOutputStream file = new FileOutputStream("CustomerDetails.bin");
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

    static void readCustomerRecordsFromFile() {
        try {
            FileInputStream file = new FileInputStream("CustomerDetails.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            while (true) {
                Customer customer = (Customer) object.readObject();
                CustomerCRUD.customers.add(customer);
                if (customer == null) {
                    break;
                }
            }
            object.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (EOFException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}

class DatabaseInvoice {
    static void addInvoiceRecordsToFile() {
        try {
            FileOutputStream file = new FileOutputStream("Invoices.bin");
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

    static void readInvoiceRecordsFromFile() {
        try {
            FileInputStream file = new FileInputStream("Invoices.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            while (true) {
                Invoice invoice = (Invoice) object.readObject();
                InvoiceCRUD.invoices.add(invoice);
                if (invoice == null) {
                    break;
                }
            }
            object.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (EOFException e) {
            System.out.println("");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}

class DatabaseProduct {
    static void addProductRecordsToFile() {
        try {
            FileOutputStream file = new FileOutputStream("Products.bin");
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

    static void readProductRecordsFromFile() {
        try {
            FileInputStream file = new FileInputStream("Products.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            while (true) {
                Product product = (Product) object.readObject();
                ProductCRUD.products.add(product);
                if (product == null) {
                    break;
                }
            }
            object.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (EOFException e) {
            System.out.println("");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
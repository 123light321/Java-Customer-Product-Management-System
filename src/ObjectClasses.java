import java.io.Serializable;

class Customer implements Serializable {
    protected int id;
    protected String name;
    protected String email;
    protected boolean gender;
    protected String city;
    protected String cellNumber;

    Customer(int id, String name, String email, boolean gender, String city, String cellNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.cellNumber = cellNumber;
    }
    String genderToString(){
        if(gender){
            return "Male";
        }else{
            return "Female";
        }
    }
}

class Product implements Serializable{
    protected int id;
    protected String name;
    protected int unit;
    protected int unitPrice;
    protected int quantityInStock;

    public Product(int id, String name, int unit, int unitPrice, int quantityInStock ) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.quantityInStock = quantityInStock;
    }
}

class Invoice implements Serializable{
    protected int invoiceId;
    protected int customerId;
    protected String date;
    protected int total;

    public Invoice(int invoiceId, int customerId, String date, int total) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.date = date;
        this.total = total;
    }
}



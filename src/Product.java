import java.io.Serializable;

/**
 * Created by shahrose on 5/24/2017.
 */
public class Product /*extends InvoiceCRUD*/ implements Serializable{
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

import javax.xml.crypto.Data;
import java.io.Serializable;

/**
 * Created by shahrose on 5/25/2017.
 */
public class Invoice implements Serializable{
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

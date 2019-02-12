import java.io.Serializable;

/**
 * Created by shahrose on 5/23/2017.
 */
public class Customer /*extends InvoiceCRUD*/  implements Serializable {
    protected int id;
    protected String name;
    protected String email;
    protected boolean gender;
    protected boolean active;
    protected String city;
    protected String cellNumber;

    public Customer(int id, String name, String email, boolean gender, boolean active, String city, String cellNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.active = active;
        this.city = city;
        this.cellNumber = cellNumber;
    }
    public String genderToString(){
        if(gender){
            return "Male";
        }else{
            return "Female";
        }
    }
    public String activeToString(){
        if(active){
            return "Active";
        }else{
            return "Female";
        }
    }
}

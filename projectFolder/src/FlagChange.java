 


//the error in here i cannot extends and how i can use it is fun


public class FlagChange extends Event{//extends User{
    //this class to get only changed price of event and customer_id to send email for him
    private String email;
    private double price;
    public FlagChange() {
    }

    public FlagChange(String email, double price, int cid, String name) {
        super(cid, name);
        this.email = email;
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getCid() {
        return super.getCid(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}

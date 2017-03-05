package business;

import data.DataMapper;
import java.sql.SQLException;

public class CustomerFacade {
    
    public static void customerSignup(String name, String email, String phone, String username, String password) throws SQLException, NullPointerException {
        DataMapper dm = new DataMapper();
        dm.customerSignup(name, email, phone, username, password);
    }
    
    public static Customer customerLogin(String username, String password) throws SQLException, NullPointerException {
        DataMapper dm = new DataMapper();
        return dm.customerLogin(username, password);
    }
    
     public static void setBalance(Customer customer, double total) throws SQLException, NullPointerException {
        DataMapper dm = new DataMapper();  
         dm.purchase(customer, total);
    }
    
}

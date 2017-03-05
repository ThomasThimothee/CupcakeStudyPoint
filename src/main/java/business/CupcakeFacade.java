package business;


import data.DataMapper;
import java.sql.SQLException;
import java.util.ArrayList;

public class CupcakeFacade {
    
    
    
    public static ArrayList<String> getToppings() throws SQLException {
        DataMapper dm = new DataMapper();
        ArrayList<String> toppings = dm.getTopping();
        return toppings;
    }
    
    public static ArrayList<String> getBottoms() throws SQLException {
        DataMapper dm = new DataMapper();
        ArrayList<String> bottoms = dm.getBottom();
        return bottoms;
    }
    
    public static Cupcake getCupcake(String topping, String bottom) throws SQLException, NullPointerException {
        DataMapper dm = new DataMapper();
        return dm.getCupcake(topping, bottom);
    }
    
}

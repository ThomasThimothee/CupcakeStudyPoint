package data;

import business.Bottom;
import business.Cupcake;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import business.Customer;
import business.Topping;
import java.util.ArrayList;

public class DataMapper {

    private final Connection con;

    public DataMapper() {
        con = new DBConnector().getConnection();
    }

    public void customerSignup(String name, String email, String phone, String username, String password) throws SQLException, NullPointerException {
        PreparedStatement createCustomer = null;
        String createCustomerString = "INSERT INTO Customer(name, email, phone, username, password) VALUES (?,?,?,?,?);";
        createCustomer = con.prepareStatement(createCustomerString);
        con.setAutoCommit(false);
        createCustomer.setString(1, name);
        createCustomer.setString(2, email);
        createCustomer.setString(3, phone);
        createCustomer.setString(4, username);
        createCustomer.setString(5, password);
        int rowAffected = createCustomer.executeUpdate();
        if (rowAffected == 1) {
            con.commit();
        } else {
            con.rollback();
        }
    }

    public Customer customerLogin(String username, String password) throws SQLException, NullPointerException {
        ResultSet rs = null;
        Customer customer = null;
        PreparedStatement getCustomer = null;
        String getCustomerString = "SELECT * FROM Customer WHERE username = ? AND password = ? ;";
        getCustomer = con.prepareStatement(getCustomerString);
        getCustomer.setString(1, username);
        getCustomer.setString(2, password);
        rs = getCustomer.executeQuery();
        if(rs.next())
        {
            customer = new Customer(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6));
        }
        else{
            throw new NullPointerException("why can't generate automatically");
        }
        return customer;
    }

    public ArrayList<String> getBottom() throws SQLException {
        ResultSet rs = null;
        ArrayList<String> list = new ArrayList();
        
        PreparedStatement getBottom = null;
        String getBottomString = "SELECT name FROM Bottom;";
        getBottom = con.prepareStatement(getBottomString);
        rs = getBottom.executeQuery();
        while (rs.next()) {
            String selectedBottom = rs.getString(1);
            list.add(selectedBottom);
        }
        return list;
    }
    
        public ArrayList<String> getTopping() throws SQLException {
        ResultSet rs = null;
        ArrayList<String> list = new ArrayList();
        
        PreparedStatement getTopping = null;
        String getToppingString = "SELECT name FROM Topping;";
        getTopping = con.prepareStatement(getToppingString);
        rs = getTopping.executeQuery();
        while (rs.next()) {
            String selectedTopping = rs.getString(1);
            list.add(selectedTopping);
        }
        return list;
    }

    public Cupcake getCupcake(String topping, String bottom) throws SQLException, NullPointerException {
        ResultSet rs = null;
        Cupcake cupcake = null;
        PreparedStatement getCupcake = null;
        String getCupcakeString = "SELECT Topping.name, Topping.price, Bottom.name, Bottom.price FROM Topping INNER JOIN Bottom WHERE Topping.name = ? AND Bottom.name = ?;";
        getCupcake = con.prepareStatement(getCupcakeString);
        getCupcake.setString(1, topping);
        getCupcake.setString(2, bottom);
        rs = getCupcake.executeQuery();
        if (rs.next()) {
            Topping selectedTopping = new Topping(rs.getString(1),
                    rs.getDouble(2));
            Bottom selectedBottom = new Bottom(rs.getString(3),
                    rs.getDouble(4));
            cupcake = new Cupcake(selectedTopping, selectedBottom);
        }
        return cupcake;
    }

    public void purchase(Customer customer, double total) throws SQLException {
        PreparedStatement purchase = null;
        double newBalance = customer.getBalance() - total;
        String purchaseString = "Update Customer set balance= ? where username = ?;";
        purchase = con.prepareStatement(purchaseString);
        con.setAutoCommit(false);
        purchase.setDouble(1, newBalance);
        purchase.setString(2, customer.getUsername());
        int rowAffected = purchase.executeUpdate();
        if (rowAffected == 1) {
            con.commit();
        } else {
            con.rollback();
        }    }
}

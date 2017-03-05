package presentation;

import business.Cupcake;
import business.CupcakeFacade;
import business.Customer;
import business.CustomerFacade;
import business.OrderLine;
import business.Topping;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Order extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String formName = request.getParameter("formName");
        HttpSession session = request.getSession();

        switch (formName) {
            case "AddCupcake":
                try {
                    if (session.getAttribute("currentUser") != null) {
                        ArrayList<OrderLine> cart = (ArrayList<OrderLine>) session.getAttribute("newCart");
                        String topping = request.getParameter("topping");
                        String bottom = request.getParameter("bottom");
                        int quantity = Integer.parseInt(request.getParameter("quantity"));
                        Cupcake cupcake = CupcakeFacade.getCupcake(topping, bottom);
                        OrderLine ol = new OrderLine(cupcake, quantity, cupcake.getPrice() * quantity);
                        cart.add(ol);
                        session.setAttribute("currentUserCart", cart);
                        request.getRequestDispatcher("shopping.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "WARNING! You need to login before buying");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } catch (SQLException | NullPointerException e) {
                    System.out.println(e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                break;

            case "finalOrder":
                double totalPrice = (double) session.getAttribute("totalPrice");
                Customer customer = (Customer) session.getAttribute("currentUser");

                if (customer.getBalance() < totalPrice) {
                    request.setAttribute("errorMessage", "WARNING! You didn't have enough credit, now you have to restart from scratch...");
                    session.invalidate();
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    try {
                        CustomerFacade.setBalance(customer, totalPrice);
                    } catch (SQLException ex) {
                        Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NullPointerException ex) {
                        Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    customer.setBalance(customer.getBalance() - totalPrice);
                    request.setAttribute("messageUserCreated", "Congratulations, your order has been registered.");
                    request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
                }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>    

}

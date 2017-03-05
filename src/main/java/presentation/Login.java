package presentation;

import business.Customer;
import business.CustomerFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String formName = request.getParameter("formName");
        
        switch (formName) {
            case "Login":
                try {   
                    System.out.println("test1");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    System.out.println("test2 name = "+username + " pass= "+password);
                    Customer customer = CustomerFacade.customerLogin(username, password);
                    System.out.println("test3");
                    session.setAttribute("currentUser", customer);
                    System.out.println("test4");
                    session.setAttribute("newCart", new ArrayList());
                    System.out.println("test5");
                    request.getRequestDispatcher("shopping.jsp").forward(request, response);
                } catch (SQLException | NullPointerException e) {
                    System.out.println("testcatch");
                    request.setAttribute("errorMessage", "Incorrect username and/or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                break;
            case "Signup":
                try {   
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    if(!name.equals("") && !email.equals("") && !phone.equals("") && !username.equals("") && !password.equals(""))
                    {    
                    CustomerFacade.customerSignup(name, email, phone, username, password);                    
                    }                  
                    Customer customer = CustomerFacade.customerLogin(username, password);
                    session.setAttribute("currentUser", customer);
                    session.setAttribute("newCart", new ArrayList());
                    request.setAttribute("messageUserCreated", "Congratulations, your new account has been created! Log in with your username and password.");
                    request.getRequestDispatcher("shopping.jsp").forward(request, response);
                } catch (SQLException | NullPointerException e) {
                    request.setAttribute("errorMessage", "Make sure that all the fields are completed");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                }
                break;
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

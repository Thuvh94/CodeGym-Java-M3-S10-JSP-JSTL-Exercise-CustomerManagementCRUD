package controller;



import model.customer;
import service.customerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerServiceServlet",urlPatterns = "/customerList")
public class customerServiceServlet extends HttpServlet {
    customerServiceImpl customerServiceObj = new customerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                addNewCustomer(request, response);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;

            default:
                displayList(request, response);
                break;
        }
    }

    private void displayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<customer> customers = customerServiceObj.findAll();
        request.setAttribute("customerList",customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayList.jsp");
        dispatcher.forward(request,response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNew.jsp");
        dispatcher.forward(request,response);
    }
    private void addNewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        boolean isMale;
        String gender = request.getParameter("gender");
        if("male".equals(gender))
            isMale = true;
        else
            isMale = false;
        List<customer> customers = customerServiceObj.findAll();
        int id = customers.size()+1;
        customer newCustomer = new customer(id,name,address,age,isMale);
        customerServiceObj.add(newCustomer);
        displayList(request,response);
    }
}

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
            case "edit":
                updateCustomer(request,response);
                break;
            case "delete":
                deleteCustomer(request,response);
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
            case "edit":
                showEditFrom(request, response);
                break;
            case "delete":
                showDeleteForm(request,response);
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
    // create function
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
    //edit function
    private void showEditFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customer editedCustomer = new customer();
        for (customer customerObj:customerServiceObj.findAll() ) {
            if(customerObj.getId()==id)
                editedCustomer = customerObj;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("customer",editedCustomer);
        dispatcher.forward(request,response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int age = Integer.parseInt(request.getParameter("age"));
        boolean isMale = false;
        customer editedCustomer = new customer(id,name,address,age,isMale);
        for (customer customerObj:customerServiceObj.findAll() ) {
            if(customerObj.getId()==id)
                customerObj = editedCustomer;
        }
        customerServiceObj.update(id,editedCustomer);
        displayList(request,response);
    }

    //delete function
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customer deletedCustomer = new customer();
        for (customer customerObj:customerServiceObj.findAll() ) {
            if(customerObj.getId()==id)
                deletedCustomer = customerObj;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
        request.setAttribute("customer",deletedCustomer);
        dispatcher.forward(request,response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerServiceObj.delete(id);
        displayList(request,response);
    }
}

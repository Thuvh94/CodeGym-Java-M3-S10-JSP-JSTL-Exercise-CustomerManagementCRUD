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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customerServiceImpl customerServiceObj = new customerServiceImpl();
        List<customer> customers = customerServiceObj.findAll();
        request.setAttribute("customerList",customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayList.jsp");
        dispatcher.forward(request,response);
    }
}

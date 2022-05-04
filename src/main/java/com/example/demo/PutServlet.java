package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String country = request.getParameter("country");
        int cost = Integer.parseInt(request.getParameter("cost"));

        Employee employee = EmployeeRepository.getEmployeeById(id);
        if(employee.getId()==0) {
            System.out.println("Response: Wrong ID\n");
            out.print("Wrong ID");
        } else {
            if(amount <= 0 || cost <= 0) {
                System.out.println("Response: Incorrect data! Amount and cost must be > 0!\n");
                out.println("Incorrect data! Amount and cost must be > 0!");
            } else {
                employee.setId(id);
                employee.setName(name);
                employee.setAmount(amount);
                employee.setCountry(country);
                employee.setCost(cost);

                int status = EmployeeRepository.update(employee);

                if (status > 0) {
                    response.sendRedirect("viewServlet");
                } else {
                    out.println("Sorry! unable to update record");
                }
            }
            out.close();
        }
    }
}

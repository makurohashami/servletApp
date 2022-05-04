package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String country = request.getParameter("country");
        int cost = Integer.parseInt(request.getParameter("cost"));

        if(amount <= 0 || cost <= 0) {
            out.println("Incorrect data! Amount and cost must be > 0!");
        } else {
            Employee employee = new Employee();

            employee.setName(name);
            employee.setAmount(amount);
            employee.setCountry(country);
            employee.setCost(cost);

            //out.println(employee.toString());
            //out.println(EmployeeRepository.getConnection());

            int status = EmployeeRepository.save(employee);
            //out.println(status);

            if (status > 0) {
                System.out.print("Response: Record saved successfully!\n");
                out.println("Record saved successfully!");
            } else {
                System.out.print("Response: Sorry! unable to save record\n");
                out.println("Sorry! unable to save record");
            }
        }
        out.close();
    }
}

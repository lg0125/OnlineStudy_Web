package Servlets.msgBoard;

import Beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/TransIdWriteMsgServlet")
public class TransIdWriteMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String teacher_id = request.getParameter("teacher_id");
        request.setAttribute("course_id",course_id);
        request.setAttribute("teacher_id",teacher_id);
        request.getRequestDispatcher("app/msgBoard/writeMsg.jsp").forward(request,response);
    }
}

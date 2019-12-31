package Servlets.root;

import Beans.Department;
import DAOs.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeDeptServlet")
public class ChangeDeptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        CourseDAO course_dao = new CourseDAO();
        Department department = course_dao.queryDept(name).get(0);

        request.setAttribute("department",department);

        request.getRequestDispatcher("app/root/changeDeptContent.jsp").forward(request,response);
    }
}

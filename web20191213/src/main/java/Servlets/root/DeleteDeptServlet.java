package Servlets.root;

import Beans.Department;
import DAOs.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteDeptServlet")
public class DeleteDeptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        CourseDAO course_dao = new CourseDAO();
        course_dao.deleteDeptAll(name);

        List<Department> department_list = course_dao.queryDepartment();

        request.setAttribute("func","delete");
        request.setAttribute("department_list",department_list);
        request.getRequestDispatcher("app/root/deptSetting.jsp").forward(request,response);
    }
}

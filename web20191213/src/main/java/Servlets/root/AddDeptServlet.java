package Servlets.root;

import Beans.Department;
import DAOs.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/AddDeptServlet")
public class AddDeptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String building = request.getParameter("building");
        BigDecimal budget = BigDecimal.valueOf(Double.parseDouble((request.getParameter("budget"))));

        CourseDAO course_dao = new CourseDAO();
        Department department = new Department();
        department.setName(name);
        department.setBuilding(building);
        department.setBudget(budget);
        course_dao.addDept(department);

        List<Department> department_list = course_dao.queryDepartment();

        request.setAttribute("func","add");
        request.setAttribute("department_list",department_list);
        request.getRequestDispatcher("app/root/deptSetting.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

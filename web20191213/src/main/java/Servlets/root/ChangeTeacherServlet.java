package Servlets.root;

import Beans.Department;
import Beans.Rank;
import Beans.Teacher;
import DAOs.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ChangeTeacherServlet")
public class ChangeTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        CourseDAO course_dao = new CourseDAO();
        Teacher teacher = course_dao.queryTeacher(id).get(0);
        List<Department> department_list = course_dao.queryDepartment();
        List<Rank> rank_list = course_dao.queryRank();

        request.setAttribute("teacher",teacher);
        request.setAttribute("department_list",department_list);
        request.setAttribute("rank_list",rank_list);
        request.getRequestDispatcher("app/root/changeTeacherContent.jsp").forward(request,response);
    }
}

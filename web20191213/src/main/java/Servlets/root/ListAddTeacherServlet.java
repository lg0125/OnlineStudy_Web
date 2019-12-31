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

@WebServlet("/ListAddTeacherServlet")
public class ListAddTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseDAO course_dao = new CourseDAO();
        List<Teacher> teacher_list = course_dao.queryTeacher();
        List<Department> department_list = course_dao.queryDepartment();
        List<Rank> rank_list = course_dao.queryRank();

        request.setAttribute("func","add");
        request.setAttribute("teacher_list",teacher_list);
        request.setAttribute("department_list",department_list);
        request.setAttribute("rank_list",rank_list);
        request.getRequestDispatcher("app/root/teacherSetting.jsp").forward(request,response);
    }
}

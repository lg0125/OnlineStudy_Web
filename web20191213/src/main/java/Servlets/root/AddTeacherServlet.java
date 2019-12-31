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

@WebServlet("/AddTeacherServlet")
public class AddTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String introduction = request.getParameter("introduction");

        String rank_name = request.getParameter("rank_name");
        if(rank_name == null) rank_name = "";

        String dept_name = request.getParameter("dept_name");
        if(dept_name == null) dept_name = "";

        CourseDAO course_dao = new CourseDAO();
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setPassword(password);
        teacher.setName(name);
        teacher.setDept_name(dept_name);
        teacher.setRank_name(rank_name);
        teacher.setIntroduction(introduction);
        course_dao.addTeacher(teacher);

        List<Teacher> teacher_list = course_dao.queryTeacher();
        List<Department> department_list = course_dao.queryDepartment();
        List<Rank> rank_list = course_dao.queryRank();

        request.setAttribute("func","add");
        request.setAttribute("teacher_list",teacher_list);
        request.setAttribute("department_list",department_list);
        request.setAttribute("rank_list",rank_list);
        request.getRequestDispatcher("app/root/teacherSetting.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

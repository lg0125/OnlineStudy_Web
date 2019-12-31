package Servlets.root;

import Beans.Teacher;
import DAOs.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ChangeTeacherContentServlet")
public class ChangeTeacherContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacher_id = request.getParameter("teacher_id");

        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String rank_name = request.getParameter("rank_name");
        String dept_name = request.getParameter("dept_name");
        String introduction = request.getParameter("introduction");

        CourseDAO course_dao = new CourseDAO();
        Teacher teacher = new Teacher();
        teacher.setId(teacher_id);
        teacher.setPassword(password);
        teacher.setName(name);
        teacher.setRank_name(rank_name);
        teacher.setDept_name(dept_name);
        teacher.setIntroduction(introduction);

        course_dao.updateTeacher(teacher);


        List<Teacher> teacher_list = course_dao.queryTeacher();

        request.setAttribute("func","change");
        request.setAttribute("teacher_list",teacher_list);
        request.getRequestDispatcher("app/root/teacherSetting.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

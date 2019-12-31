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

@WebServlet("/DeleteTeacherServlet")
public class DeleteTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        CourseDAO course_dao = new CourseDAO();

        course_dao.deleteTakesTeacherId(id);
        course_dao.deleteTeaches(id);
        course_dao.deleteTeacher(id);

        List<Teacher> teacher_list = course_dao.queryTeacher();

        request.setAttribute("func","delete");
        request.setAttribute("teacher_list",teacher_list);
        request.getRequestDispatcher("app/root/teacherSetting.jsp").forward(request,response);
    }
}

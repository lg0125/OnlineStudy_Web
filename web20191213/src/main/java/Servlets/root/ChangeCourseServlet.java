package Servlets.root;

import Beans.Course;
import Beans.Department;
import Beans.Teacher;
import DAOs.CourseDAO;
import Views.TeacherCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ChangeCourseServlet")
public class ChangeCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course_id = request.getParameter("course_id");

        CourseDAO course_dao = new CourseDAO();
        Course course = course_dao.query(course_id).get(0);


        List<Department> department_list = course_dao.queryDepartment();
        List<Teacher> teacher_list = course_dao.queryTeacher();

        request.setAttribute("course",course);

        request.setAttribute("department_list",department_list);
        request.setAttribute("teacher_list",teacher_list);

        request.getRequestDispatcher("app/root/changeCourseContent.jsp").forward(request,response);
    }
}

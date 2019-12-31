package Servlets.root;

import Beans.Course;
import DAOs.CourseDAO;
import Views.TeacherCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ChangeCourseContentServlet")
public class ChangeCourseContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course_id = request.getParameter("course_id");

        String name = request.getParameter("name");
        String dept_name = request.getParameter("dept_name");
        String introduction = request.getParameter("introduction");

        CourseDAO course_dao = new CourseDAO();
        Course course = new Course();
        course.setId(course_id);
        course.setName(name);
        course.setDept_name(dept_name);
        course.setIntroduction(introduction);

        course_dao.updateCourse(course);

        List<TeacherCourse> teacher_course_list = course_dao.queryTeacherCourseOrderCourse();

        request.setAttribute("func","change");
        request.setAttribute("teacher_course_list",teacher_course_list);
        request.getRequestDispatcher("app/root/courseSetting.jsp").forward(request,response);
    }
}

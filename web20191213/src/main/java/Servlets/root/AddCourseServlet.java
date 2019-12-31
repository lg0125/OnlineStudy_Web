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

@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String dept_name = request.getParameter("dept_name");
        String introduction = request.getParameter("introduction");

        String temp = request.getParameter("teacher_id");
        String teacher_id = temp.substring(0,temp.indexOf(":")); // <

        //course
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDept_name(dept_name);
        course.setIntroduction(introduction);

        //teaches
        CourseDAO course_dao = new CourseDAO();
        course_dao.add(course);
        course_dao.addTeaches(teacher_id,id);

        List<TeacherCourse> teacher_course_list = course_dao.queryTeacherCourseOrderCourse();
        List<Department> department_list = course_dao.queryDepartment();
        List<Teacher> teacher_list = course_dao.queryTeacher();

        request.setAttribute("func","add");
        request.setAttribute("department_list",department_list);
        request.setAttribute("teacher_course_list",teacher_course_list);
        request.setAttribute("teacher_list",teacher_list);
        request.getRequestDispatcher("app/root/courseSetting.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

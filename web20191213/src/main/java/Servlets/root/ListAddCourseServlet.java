package Servlets.root;

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

@WebServlet("/ListAddCourseServlet")
public class ListAddCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseDAO course_dao = new CourseDAO();
        List<TeacherCourse> teacher_course_list = course_dao.queryTeacherCourseOrderCourse();
        List<Department> department_list = course_dao.queryDepartment();
        List<Teacher> teacher_list = course_dao.queryTeacher();

        request.setAttribute("func","add");
        request.setAttribute("department_list",department_list);
        request.setAttribute("teacher_course_list",teacher_course_list);
        request.setAttribute("teacher_list",teacher_list);
        request.getRequestDispatcher("app/root/courseSetting.jsp").forward(request,response);
    }
}

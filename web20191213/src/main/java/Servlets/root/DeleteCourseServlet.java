package Servlets.root;

import DAOs.CourseDAO;
import Views.TeacherCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String teacher_id = request.getParameter("teacher_id");

        CourseDAO course_dao = new CourseDAO();

        course_dao.deleteCourseByCourseTeacherId(course_id,teacher_id);
        course_dao.delete(course_id);

        List<TeacherCourse> teacher_course_list = course_dao.queryTeacherCourseOrderCourse();

        request.setAttribute("func","delete");
        request.setAttribute("teacher_course_list",teacher_course_list);
        request.getRequestDispatcher("app/root/courseSetting.jsp").forward(request,response);
    }
}

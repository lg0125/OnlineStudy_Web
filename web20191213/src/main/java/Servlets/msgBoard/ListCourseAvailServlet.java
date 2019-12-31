package Servlets.msgBoard;

import Beans.User;
import DAOs.CourseDAO;
import Views.TeacherCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ListCourseAvailServlet")
public class ListCourseAvailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");
        String teacher_id = user.getId();

        CourseDAO course_dao = new CourseDAO();
        ArrayList<TeacherCourse> teacher_course_list = course_dao.queryTeacherCourse(teacher_id);
        request.setAttribute("func","avail");
        request.setAttribute("teacher_course_list",teacher_course_list);
        request.getRequestDispatcher("app/msgBoard/msgBoardContent.jsp").forward(request,response);
    }
}

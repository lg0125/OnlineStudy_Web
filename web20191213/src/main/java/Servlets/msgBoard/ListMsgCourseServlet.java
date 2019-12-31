package Servlets.msgBoard;

import Beans.User;
import DAOs.CourseDAO;
import Views.StudentCourse;
import Views.TeacherCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ListMsgCourseServlet")
public class ListMsgCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");
        String student_id = user.getId();

        CourseDAO course_dao = new CourseDAO();
        ArrayList<StudentCourse> student_course_list = course_dao.queryStudentCourse(student_id);
        request.setAttribute("student_course_list",student_course_list);
        request.getRequestDispatcher("app/msgBoard/listMsgCourse.jsp").forward(request,response);
    }
}

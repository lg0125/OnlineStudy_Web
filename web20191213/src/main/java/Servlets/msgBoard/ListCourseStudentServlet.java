package Servlets.msgBoard;

import Beans.User;
import DAOs.CourseDAO;
import Views.CourseStudent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ListCourseStudentServlet")
public class ListCourseStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");
        String teacher_id = user.getId();
        String course_id = request.getParameter("course_id");

        CourseDAO course_dao = new CourseDAO();
        ArrayList<CourseStudent> course_studentAvail_list = course_dao.queryCourseStudentAvail(course_id,teacher_id);
        ArrayList<CourseStudent> course_studentNotAvail_list = course_dao.queryCourseStudentNotAvail(course_id,teacher_id);

        request.setAttribute("course_studentAvail_list",course_studentAvail_list);
        request.setAttribute("course_studentNotAvail_list",course_studentNotAvail_list);
        request.getRequestDispatcher("app/msgBoard/courseStudentAvail.jsp").forward(request,response);
    }
}

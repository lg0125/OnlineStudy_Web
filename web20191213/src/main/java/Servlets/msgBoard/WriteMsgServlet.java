package Servlets.msgBoard;

import Beans.Message;
import Beans.User;
import DAOs.CourseDAO;
import DAOs.MessageDAO;
import Views.StudentCourse;
import Views.TeacherCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


@WebServlet("/WriteMsgServlet")
public class WriteMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");
        String student_id = user.getId();
        String course_id = request.getParameter("course_id");
        String teacher_id = request.getParameter("teacher_id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Date time = new Date(new java.util.Date().getTime());

        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        message.setTime(time);
        message.setStudent_id(student_id);
        message.setCourse_id(course_id);
        message.setTeacher_id(teacher_id);

        MessageDAO message_dao = new MessageDAO();
        message_dao.add(message);

        CourseDAO course_dao = new CourseDAO();
        ArrayList<StudentCourse> student_course_list = course_dao.queryStudentCourse(student_id);
        request.setAttribute("student_course_list",student_course_list);
        request.getRequestDispatcher("app/msgBoard/listMsgCourse.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

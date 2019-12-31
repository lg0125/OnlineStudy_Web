package Servlets.msgBoard;

import Beans.User;
import DAOs.CourseDAO;
import DAOs.MessageDAO;
import Views.MessageShow;
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

@WebServlet("/GoMsgBoardServlet")
public class GoMsgBoardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");

        Integer notReply = 0;
        Integer Reply = 1;

        MessageDAO message_dao = new MessageDAO();
        CourseDAO course_dao = new CourseDAO();
        ArrayList<MessageShow> message_list = null;
        ArrayList<TeacherCourse> teacher_course_list = null;
        ArrayList<StudentCourse> student_course_list = null;
        int notReplyNum = 0;
        int ReplyNum = 0;
        if(user.getType().equals("student")){
            String student_id = user.getId();
            String setting = request.getParameter("setting");
            if(setting == null) setting = "";
            message_list = message_dao.queryReplyStudent(Reply,student_id);
            ReplyNum = message_list.size();
            if(!setting.equals("")) {
                if(setting.equals("teacher")){
                    student_course_list = course_dao.queryStudentCourseOrderBYTeacher(student_id);
                }else if(setting.equals("department")){
                    student_course_list = course_dao.queryStudentCourseOrderBYDept(student_id);
                }else{
                    student_course_list = course_dao.queryStudentCourse(student_id);
                }
            }else{
                student_course_list = course_dao.queryStudentCourse(student_id);
            }

            request.setAttribute("student_course_list",student_course_list);
        }else if(user.getType().equals("teacher")){
            String teacher_id = user.getId();
            message_list = message_dao.queryReplyTeacher(teacher_id,notReply);
            notReplyNum = message_list.size();
            teacher_course_list = course_dao.queryTeacherCourse(teacher_id);
            request.setAttribute("teacher_course_list",teacher_course_list);
        }else{
            teacher_course_list = course_dao.queryTeacherCourseOrderCourse();
            request.setAttribute("teacher_course_list",teacher_course_list);
        }

        session.setAttribute("notReplyNum",notReplyNum);
        session.setAttribute("ReplyNum",ReplyNum);
        request.getRequestDispatcher("app/msgBoard/msgBoardContent.jsp").forward(request,response);
    }
}

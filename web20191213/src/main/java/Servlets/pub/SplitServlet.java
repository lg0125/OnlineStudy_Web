package Servlets.pub;

import Beans.*;
import DAOs.CourseDAO;
import DAOs.MessageDAO;
import DAOs.ReplyDAO;
import Views.MessageShow;
import Views.ReplyShow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/SplitServlet")
public class SplitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");
        String type = user.getType();
        String id = user.getId();

        CourseDAO course_dao = new CourseDAO();
        MessageDAO message_dao = new MessageDAO();
        ReplyDAO reply_dao = new ReplyDAO();
        Course course = null;
        ArrayList<MessageShow> message_list = null;
        HashMap<Long,ArrayList<ReplyShow> > reply_map = null;

        if(type.equals("root")){
            String course_id = (String) session.getAttribute("course_id");
            String teacher_id = (String) session.getAttribute("teacher_id");
            ArrayList<Course> temp = course_dao.query(course_id);
            course = temp.get(0);
            message_list = message_dao.queryByCourse(course_id,teacher_id);
            reply_map = reply_dao.getReplyMap(message_list);
        }else if(type.equals("student")){
            String student_id = user.getId();
            String funcPlus = (String) session.getAttribute("funcPlus");
            if(funcPlus == null) funcPlus = "";
            if(!funcPlus.equals("Reply")){
                String func = (String) session.getAttribute("func");
                if(func == null) func = "";
                if(func.equals("")){
                    message_list = message_dao.queryByStudentId(student_id);
                    reply_map = reply_dao.getReplyMap(message_list);
                }else{
                    ScanMsgReply scanMsgReply = (ScanMsgReply) session.getAttribute("ScanMsgReply");

                    List<Course> course_list = course_dao.query();
                    List<Teacher> teacher_list = course_dao.queryTeacher();
                    List<Department> department_list = course_dao.queryDepartment();
                    request.setAttribute("course_list",course_list);
                    request.setAttribute("teacher_list",teacher_list);
                    request.setAttribute("department_list",department_list);

                    message_list = message_dao.queryByCondition(scanMsgReply.getCourse_name(),scanMsgReply.getCourse_dept(),scanMsgReply.getTeacher_name(),scanMsgReply.getTitle(),scanMsgReply.getContent());
                    reply_map = reply_dao.getReplyMap(message_list);
                }
            }else{
                Integer replied = 1;
                message_list = message_dao.queryReplyStudent(replied,student_id);
                reply_map = reply_dao.getReplyMap(message_list);
            }
        }else{
            String teacher_id = user.getId();
            String funcPlus =  (String) session.getAttribute("funcPlus");
            if(funcPlus == null) funcPlus = "";
            Integer notReplied = 0;
            Integer replied = 1;
            if(!funcPlus.equals("notReply")){
                message_list = message_dao.queryReplyTeacher(teacher_id,notReplied);
                ArrayList<MessageShow> temp = message_dao.queryReplyTeacher(teacher_id,replied);
                message_list.addAll(temp);
                reply_map = reply_dao.getReplyMap(message_list);
            }else{
                message_list = message_dao.queryReplyTeacher(teacher_id,notReplied);
                reply_map = reply_dao.getReplyMap(message_list);
            }
        }


        if(course != null)  request.setAttribute("course",course);
        Integer current = Integer.parseInt(request.getParameter("current"));
        request.setAttribute("current",current);
        request.setAttribute("message_list",message_list);
        request.setAttribute("reply_map",reply_map);
        request.getRequestDispatcher("app/msgBoard/msgReply.jsp").forward(request,response);
    }
}

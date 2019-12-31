package Servlets.msgBoard;

import Beans.Course;
import Beans.Message;
import Beans.SplitPage;
import Beans.User;
import DAOs.CourseDAO;
import DAOs.MessageDAO;
import DAOs.ReplyDAO;
import Views.MessageShow;
import Views.ReplyShow;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ChangeMsgServlet")
public class ChangeMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

       String title = request.getParameter("title");
       String content = request.getParameter("content");
       Integer message_id = Integer.parseInt(request.getParameter("message_id"));

       //操作
       message_dao.updateTitle(message_id,title);
       message_dao.updateContent(message_id,content);

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
                message_list = message_dao.query();
                reply_dao.getReplyMap(message_list);
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
       Integer pageSize = 3;
       Integer rowCount = message_list.size();
       Integer pageCount = 0;
       if(rowCount < pageSize) pageCount = 1;
       else pageCount = (rowCount - 1) / pageSize + 1;
       SplitPage sp = new SplitPage();
       sp.setRowCount(rowCount);
       sp.setPageSize(pageSize);
       sp.setPageCount(pageCount);
       session.setAttribute("splitPage",sp);

       if(course != null)  request.setAttribute("course",course);
       Integer current =  0 ;
       request.setAttribute("current",current);
       request.setAttribute("message_list",message_list);
       request.setAttribute("reply_map",reply_map);
       request.getRequestDispatcher("app/msgBoard/msgReply.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package Servlets.msgBoard;

import Beans.*;
import DAOs.CourseDAO;
import DAOs.MessageDAO;
import DAOs.ReplyDAO;
import Views.MessageShow;
import Views.ReplyShow;
import com.mysql.cj.protocol.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/ScanMsgReplyServlet")
public class ScanMsgReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String course_name = request.getParameter("course_name");
        if(course_name == null) course_name = "";
        String course_dept = request.getParameter("course_dept");
        if(course_dept == null) course_dept = "";
        String teacher_name = request.getParameter("teacher_name");
        if(teacher_name == null) course_dept = "";
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        MessageDAO message_dao = new MessageDAO();
        ReplyDAO reply_dao = new ReplyDAO();

        ArrayList<MessageShow> message_list= message_dao.queryByCondition(course_name,course_dept,teacher_name,title,content);
        Map<Long, ArrayList<ReplyShow> > reply_map = reply_dao.getReplyMap(message_list);

        ScanMsgReply scanMsgReply = new ScanMsgReply();
        scanMsgReply.setTitle(title);
        scanMsgReply.setContent(content);
        scanMsgReply.setTeacher_name(teacher_name);
        scanMsgReply.setCourse_dept(course_dept);
        scanMsgReply.setCourse_name(course_name);
        session.setAttribute("ScanMsgReply",scanMsgReply);

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

        CourseDAO course_dao = new CourseDAO();
        List<Course> course_list = course_dao.query();
        List<Teacher> teacher_list = course_dao.queryTeacher();
        List<Department> department_list = course_dao.queryDepartment();
        request.setAttribute("course_list",course_list);
        request.setAttribute("teacher_list",teacher_list);
        request.setAttribute("department_list",department_list);


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

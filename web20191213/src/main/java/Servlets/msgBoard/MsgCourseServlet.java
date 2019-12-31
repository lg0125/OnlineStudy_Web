package Servlets.msgBoard;

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
import java.util.Map;

@WebServlet("/MsgCourseServlet")
public class MsgCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String course_id = request.getParameter("course_id");
        String teacher_id = request.getParameter("teacher_id");

        CourseDAO course_dao = new CourseDAO();
        ArrayList<Course> temp = course_dao.query(course_id);
        Course course = temp.get(0);

        MessageDAO message_dao = new MessageDAO();
        ArrayList<MessageShow> message_list = message_dao.queryByCourse(course_id,teacher_id);

        ReplyDAO reply_dao = new ReplyDAO();
        Map<Long,ArrayList<ReplyShow> > reply_map = reply_dao.getReplyMap(message_list);

        Integer current =  0 ;

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

        session.setAttribute("funcPlus","");
        session.setAttribute("func","");

        session.setAttribute("course_id",course_id);
        session.setAttribute("teacher_id",teacher_id);

        session.setAttribute("course",course);

        request.setAttribute("current",current);
        request.setAttribute("message_list",message_list);
        request.setAttribute("reply_map",reply_map);
        request.getRequestDispatcher("app/msgBoard/msgReply.jsp").forward(request,response);
    }
}

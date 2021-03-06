package Servlets.msgBoard;

import Beans.SplitPage;
import Beans.User;
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
import java.util.Map;

@WebServlet("/ListMsgTeacherServlet")
public class ListMsgTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");
        String teacher_id = user.getId();

        Integer notReplied = 0;
        Integer replied = 1;
        MessageDAO message_dao = new MessageDAO();
        ArrayList<MessageShow> message_list = message_dao.queryReplyTeacher(teacher_id,notReplied);
        ArrayList<MessageShow> temp = message_dao.queryReplyTeacher(teacher_id,replied);
        message_list.addAll(temp);

        ReplyDAO reply_dao = new ReplyDAO();
        Map<Long,ArrayList<ReplyShow> > reply_map = reply_dao.getReplyMap(message_list);

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

        Integer current =  0 ;
        request.setAttribute("current",current);
        request.setAttribute("message_list",message_list);
        request.setAttribute("reply_map",reply_map);
        request.getRequestDispatcher("app/msgBoard/msgReply.jsp").forward(request,response);
    }
}

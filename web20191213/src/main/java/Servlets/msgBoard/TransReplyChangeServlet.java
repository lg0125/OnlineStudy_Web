package Servlets.msgBoard;

import DAOs.ReplyDAO;
import Views.ReplyShow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/TransReplyChangeServlet")
public class TransReplyChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer reply_id = Integer.parseInt(request.getParameter("reply_id"));
        ReplyDAO reply_dao = new ReplyDAO();
        ReplyShow reply = reply_dao.query(reply_id).get(0);
        request.setAttribute("func","change");
        request.setAttribute("Reply",reply);
        request.getRequestDispatcher("app/msgBoard/writeReply.jsp").forward(request,response);
    }
}

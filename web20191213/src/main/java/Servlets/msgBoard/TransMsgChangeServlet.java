package Servlets.msgBoard;

import Beans.Message;
import DAOs.MessageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/TransMsgChangeServlet")
public class TransMsgChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer message_id = Integer.parseInt(request.getParameter("message_id"));
        MessageDAO message_dao = new MessageDAO();
        Message message = message_dao.queryMessage(message_id).get(0);
        request.setAttribute("func","change");
        request.setAttribute("Message",message);
        request.getRequestDispatcher("app/msgBoard/writeMsg.jsp").forward(request,response);
    }
}

package Servlets.msgBoard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TransWriteReplyServlet")
public class TransWriteReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message_id = request.getParameter("message_id");
        request.setAttribute("message_id",message_id);
        request.getRequestDispatcher("app/msgBoard/writeReply.jsp").forward(request,response);
    }
}

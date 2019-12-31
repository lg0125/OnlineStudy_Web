package Servlets.pub;

import Beans.User;
import DAOs.PublicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        PublicDAO public_dao = new PublicDAO();
        ArrayList<User> user_list = public_dao.signIn_query(id,password);

        if(user_list.size() == 0){
            request.getRequestDispatcher("app/include/signIn.jsp").forward(request,response);
        }
        else{
            Cookie cookie_id = new Cookie("id",id);
            Cookie cookie_password = new Cookie("password",password);
            cookie_id.setMaxAge(60*60);
            cookie_password.setMaxAge(60*60);
            response.addCookie(cookie_id);
            response.addCookie(cookie_password);

            HttpSession session = request.getSession();
            session.setAttribute("user_info",user_list.get(0));
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

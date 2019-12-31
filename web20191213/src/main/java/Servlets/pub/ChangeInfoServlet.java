package Servlets.pub;

import Beans.User;
import DAOs.PublicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ChangeInfoServlet")
public class ChangeInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_info");
        String id = user.getId();
        String type = user.getType();

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        PublicDAO public_dao = new PublicDAO();
        ArrayList<User> user_list = public_dao.signIn_query(id,oldPassword);
        if(user_list == null){
            request.getRequestDispatcher("app/role/changeInfo.jsp").forward(request,response);
        }else{
            public_dao.updateUserInfo(id,newPassword);

            if(type.equals("student")){
                public_dao.updateStudentInfo(id,newPassword);
            }else if(type.equals("teacher")){
                public_dao.updateTeacherInfo(id,newPassword);
            }else{
                public_dao.updateRootInfo(id,newPassword);
            }

            request.getRequestDispatcher("app/role/roleContent.jsp").forward(request,response);
        }

    }
}

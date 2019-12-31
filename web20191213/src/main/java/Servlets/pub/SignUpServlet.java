package Servlets.pub;

import Beans.Student;
import DAOs.PublicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dept_name = request.getParameter("dept_name");
        String introduction = request.getParameter("introduction");

        PublicDAO public_dao = new PublicDAO();
        Student student = new Student();
        student.setId(id);
        student.setPassword(password);
        student.setName(name);
        student.setDept_name(dept_name);
        student.setIntroduction(introduction);

        //操作
        Boolean ok = public_dao.addStudent(student);

        if(ok){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/GoSignUpServlet").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

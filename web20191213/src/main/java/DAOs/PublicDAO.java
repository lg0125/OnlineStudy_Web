package DAOs;

import Beans.Student;
import Beans.Teacher;
import Beans.User;

import java.util.ArrayList;

public class PublicDAO extends BaseDAO {

    public ArrayList<User> signIn_query(String id, String password){

        Class<User> clazz = User.class;

        String sql = "SELECT * FROM user where id=? and password=? ";

        Object[] param = {id,password};

        return executeQuery(clazz,sql,param.length,param);

    }

    public Boolean updateUserInfo(String id,String newPassword){

        String sql = "UPDATE user SET password=? WHERE id=? ";

        Object[] param = {newPassword,id};

        return executeStatement(sql,param);

    }

    public Boolean updateStudentInfo(String id,String newPassword){

        String sql = "UPDATE student SET password=? WHERE id=? ";

        Object[] param = {newPassword,id};

        return executeStatement(sql,param);

    }

    public Boolean updateTeacherInfo(String id,String newPassword){

        String sql = "UPDATE teacher SET password=? WHERE id=? ";

        Object[] param = {newPassword,id};

        return executeStatement(sql,param);

    }

    public Boolean updateRootInfo(String id,String newPassword){

        String sql = "UPDATE root SET password=? WHERE id=? ";

        Object[] param = {newPassword,id};

        return executeStatement(sql,param);

    }

    public Boolean updateStudentPhoto(String id,String photo){

        String sql = "UPDATE student SET photo=? WHERE id=? ";

        Object[] param = {photo,id};

        return executeStatement(sql,param);

    }

    public Boolean updateTeacherPhoto(String id,String photo){

        String sql = "UPDATE teacher SET photo=? WHERE id=? ";

        Object[] param = {photo,id};

        return executeStatement(sql,param);

    }

    public Boolean addStudent(Student student){

        String sql = "INSERT INTO student (id,password,name,dept_name,introduction) VALUES (?,?,?,?,?) ";

        String sqlUser = "INSERT INTO user (id,password,type) VALUES (?,?,'student') ";

        Object[] param = {student.getId(),student.getPassword(),student.getName(),student.getDept_name(),student.getIntroduction()};

        Object[] paramUser = {student.getId(),student.getPassword()};

        return executeStatement(sql,param) && executeStatement(sqlUser,paramUser);

    }

}

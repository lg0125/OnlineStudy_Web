package DAOs;



import Beans.Message;
import Views.MessageShow;

import java.util.ArrayList;

public class MessageDAO extends BaseDAO {

    public ArrayList<MessageShow> query(){

        Class<MessageShow> clazz = MessageShow.class;

        String sql = "SELECT * FROM messageShow ";

        Object[] param = {};

        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<MessageShow> queryReplyStudent(Integer status, String student_id){

        Class<MessageShow> clazz = MessageShow.class;

        String sql = "SELECT * FROM messageShow WHERE status=? AND student_id=? ";

        Object[] param = {status,student_id};

        return executeQuery(clazz,sql,param.length,param);

    }

    public ArrayList<MessageShow> queryReplyTeacher(String teacher_id,Integer status){

        Class<MessageShow> clazz = MessageShow.class;

        String sql = "SELECT * FROM messageShow WHERE status=? AND teacher_id=? ";

        Object[] param = {status,teacher_id};

        return executeQuery(clazz,sql,param.length,param);

    }

    public ArrayList<MessageShow> queryByCondition(String course_name,String course_dept,String teacher_name,String title,String content){

        Class<MessageShow> clazz = MessageShow.class;

        String sql = "SELECT * FROM messageShow WHERE 1 ";
        String sql_courseName = " and 1 ";
        String sql_courseDept = " and 1 ";
        String sql_teacherName = " and 1 ";
        String sql_title = " and 1 ";
        String sql_content = " and 1 ";

        int i = 0;
        Object[] param = new Object[5];

        if(!course_name.equals("")){
            sql_courseName = " and course_name like ? ";
            param[i++] = "%"+course_name+"%";
        }
        if(!course_dept.equals("")){
            sql_courseDept = " and course_dept like ? ";
            param[i++] = "%"+course_dept+"%";
        }
        if(!teacher_name.equals("")){
            sql_teacherName = " and teacher_name like ? ";
            param[i++] = "%"+teacher_name+"%";
        }
        if(!title.equals("")){
            sql_title = " and title like ? ";
            param[i++] = "%"+title+"%";
        }
        if(!content.equals("")){
            sql_content = " and content like ? ";
            param[i++] = "%"+course_dept+"%";
        }

        sql += sql_courseName + sql_courseDept + sql_teacherName + sql_title + sql_content;

        return executeQuery(clazz,sql,i,param);

    }

    public ArrayList<MessageShow> queryByCourse(String course_id,String teacher_id){

        Class<MessageShow> clazz = MessageShow.class;

        String sql = "SELECT * FROM messageShow WHERE course_id=? AND teacher_id=? ";

        Object[] param = {course_id,teacher_id};

        return executeQuery(clazz,sql,param.length,param);

    }

    public ArrayList<Message> queryMessageByCourse(String course_id,String teacher_id){

        Class<Message> clazz = Message.class;

        String sql = "SELECT * FROM message WHERE course_id=? AND teacher_id=? ";

        Object[] param = {course_id,teacher_id};

        return executeQuery(clazz,sql,param.length,param);

    }


    public ArrayList<MessageShow> queryByStudentId(String student_id){

        Class<MessageShow> clazz = MessageShow.class;

        String sql = "SELECT * FROM messageShow WHERE student_id=? ";

        Object[] param = {student_id};

        return executeQuery(clazz,sql,param.length,param);

    }

    public Boolean add(Message message){

        String sql = "INSERT INTO message (title,content,time,course_id,teacher_id,student_id) "
                    +"VALUES (?,?,?,?,?,?) ";

        Object[] param = {message.getTitle(),message.getContent(),message.getTime(),message.getCourse_id(),message.getTeacher_id(),message.getStudent_id()};

        return executeStatement(sql,param);

    }

    public Boolean delete(Integer id){

        String sql = "DELETE FROM message WHERE id=? ";

        Object[] param = {id};

        return executeStatement(sql,param);

    }

    public Boolean updateTitle(Integer id,String title){

        String sql = "UPDATE message SET title=? WHERE id=? ";

        Object[] param = {title,id};

        return executeStatement(sql,param);

    }

    public Boolean updateContent(Integer id,String content){

        String sql = "UPDATE message SET content=? WHERE id=? ";

        Object[] param = {content,id};

        return executeStatement(sql,param);

    }

    public ArrayList<Message> queryMessage(Integer id){

        Class<Message> clazz = Message.class;

        String sql = "SELECT * FROM message WHERE id=? ";

        Object[] param = {id};

        return executeQuery(clazz,sql,param.length,param);

    }

    public Boolean updateStatus(Integer message_id){

        String sql = "UPDATE message SET status=1 WHERE id=? ";

        Object[] param = {message_id};

        return executeStatement(sql,param);

    }

}

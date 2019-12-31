package DAOs;

import Beans.Reply;
import Views.MessageShow;
import Views.ReplyShow;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReplyDAO extends BaseDAO {

    public ArrayList<ReplyShow> query(Integer id){

        Class<ReplyShow> clazz = ReplyShow.class;

        String sql = "SELECT * FROM replyShow WHERE id=? ";

        Object[] param = {id};

        return executeQuery(clazz,sql,param.length,param);

    }

    public ArrayList<ReplyShow> queryMessageID(Integer message_id){

        Class<ReplyShow> clazz = ReplyShow.class;

        String sql = "SELECT * FROM replyShow WHERE message_id=? ";

        Object[] param = {message_id};

        return executeQuery(clazz,sql,param.length,param);

    }

    public HashMap<Long,ArrayList<ReplyShow> > getReplyMap(ArrayList<MessageShow> message_list){

        HashMap<Long,ArrayList<ReplyShow> > reply_map = new HashMap<>();

        for(MessageShow messageShow:message_list){
            Integer message_id = messageShow.getId();
            ArrayList<ReplyShow> reply_list = queryMessageID(message_id);
            reply_map.put(message_id.longValue(),reply_list);
        }

        return reply_map;
    }

    public Boolean deleteByMessageId(Integer message_id){

        String sql = "DELETE FROM reply WHERE message_id=? ";

        Object[] param = {message_id};

        return executeStatement(sql,param);

    }

    public Boolean delete(Integer id){

        String sql = "DELETE FROM reply WHERE id=? ";

        Object[] param = {id};

        return executeStatement(sql,param);

    }

    public Boolean update(String content,Integer id){

        String sql = "UPDATE reply SET content=? WHERE id=? ";

        Object[] param = {content,id};

        return executeStatement(sql,param);

    }

    public Boolean add(String content, Date time,Integer message_id){

        String sql = "INSERT INTO reply (content,time,message_id) "
                    +"VALUES (?,?,?) ";

        Object[] param = {content,time,message_id};

        return executeStatement(sql,param);

    }
}

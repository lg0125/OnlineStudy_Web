package DAOs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

public class BaseDAO {
    DataSource dataSource = null;

    public BaseDAO(){
        try{
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/myJDBC");
        }catch (NamingException e){
            System.out.println(e.getMessage());
        }
    }

    private void invoke(Object obj,String name,Object...param){
        Class clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        try{
            for(Method method:methods){
                if(method.getName().equalsIgnoreCase(name)){
                    method.invoke(obj,param);
                    return;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    <T> ArrayList<T> executeQuery(Class<T> clazz, String sql, int length, Object... param){

        ArrayList<T> result = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            if(length > 0){
                for(int i = 0;i<length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            try(
                    ResultSet rs = pstmt.executeQuery())
            {
                ResultSetMetaData rsmd = rs.getMetaData();
                int column = rsmd.getColumnCount();
                while(rs.next())
                {
                    T t = clazz.getDeclaredConstructor().newInstance();
                    for(int j = 0;j<column;j++){
                        invoke(t,"set"+rsmd.getColumnName(j+1),rs.getObject(j+1));
                    }
                    result.add(t);
                }
                return result;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    Boolean executeStatement(String sql,Object... param){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            for(int i = 0;i<param.length;i++){
                pstmt.setObject(i+1,param[i]);
            }
            return pstmt.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}

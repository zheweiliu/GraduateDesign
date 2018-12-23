package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class queryResult {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "liuzhewei", "123456");
            System.out.println("Opened database successfully");

            System.out.println("是否成功连接pg数据库" + connection);

            /*
            将PIXID结果保存到数组中
             */

            String sql0 = "select pixid from t_gist group by pixid order by pixid asc ";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql0);
//            connection.commit();
//            connection.setAutoCommit(true);
            List<String> list=new ArrayList<String>();
            //创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
            while (rs.next()) {
                //如果有数据，取第一列添加如list
                list.add(rs.getString(1));
            }
            String[] arr = null;
            if(list != null && list.size()>0) {
                //如果list中存入了数据，转化为数组
                arr = new String[list.size()];
                //创建一个和list长度一样的数组
                for (int i = 0; i < list.size(); i++) {
                    arr[i] = list.get(i);//数组赋值了。
                }
                for(int i=0;i<arr.length;i++){
                    System.out.println(arr[i]);
                }
            }

            int num = list.size();

            for(int i=0; i<num;i++ ){
                if(arr != null) {
                    String sql = "select * into ucac" + i + " from t_gist where pixid = '" + arr[i].trim()+"'";
                    System.out.println(sql);
                    statement = connection.createStatement();
                    statement.execute(sql);
                }
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

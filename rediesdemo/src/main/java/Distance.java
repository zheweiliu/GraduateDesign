import java.sql.*;

public class Distance {
    public static void main(String[] args)throws Exception {
        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            String url="jdbc:mysql://localhost:3306/test";    //JDBC的URL
            Connection conn;

            conn = DriverManager.getConnection(url,"root","123456");
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = "select * from UcacNGC1664p ";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("u_ucac4");
            while (rs.next()){
                System.out.print(rs.getInt(1) + "\t");
                System.out.println();
            }


        } catch (ClassNotFoundException e1) {
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }
    }
}

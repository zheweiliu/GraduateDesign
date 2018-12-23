package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Collections;
import java.math.MathContext;

public class Knn {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        Statement state = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "liuzhewei", "123456");

            System.out.println("Opened database successfully");

            /**
             * 取ucac(t_gist)中的所有pixid存成list
             */
            String sql0 = "select pixid from ucactest group by pixid order by pixid asc ";

            PreparedStatement ps = connection.prepareStatement(sql0);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);

            List<String> list=new ArrayList<String>();
            while (rs.next()) {
                //如果有数据，取第一列添加如list
                list.add(rs.getString(1));
            }
            System.out.println(list.size());
            System.out.println(list.get(1));

            /**
             * -------------------以下全部是在每个pixid中KNN近似查询RA---------------------------
             * ----------------------------要加pixid循环------------------------------------
             */

            /**
             * 找RA最大最小值
             */
            String sql1= "select ra,point from ucactest where pixid = '116840' order by ra asc";

            ps = connection.prepareStatement(sql1);
            ResultSet rs2 = ps.executeQuery();
            System.out.println(rs2);

            List<Double> RA=new ArrayList<Double>();
            List<String> str=new ArrayList<String>();

            while (rs2.next()) {
                //如果有数据，取第一列添加如list
                RA.add((rs2.getDouble(1)));
                str.add(rs2.getString(2));
            }
            int num = RA.size();
            System.out.println(RA.size());
            System.out.println(RA.get(0));  //RAmin
            System.out.println(RA.get(num-1));  //RAmax
            System.out.println("point长度为:"+str.size());
            System.out.println(RA.get(0) + "  " +str.get(0));

            double x = RA.get(num-1) - RA.get(0);
            System.out.println(x);

            /**
             * 保存point
             */
            List<Point> pl=new ArrayList<Point>();

//            for(int i=0; i<num; i++){
                String strs[] = str.get(num -1).substring(1,str.get(0).length()-1).split(",");
//                System.out.println("x = "+strs[0]);
//                System.out.println("y = "+strs[1]);

                BigDecimal pointra = new BigDecimal(strs[0]);
                BigDecimal pointde = new BigDecimal(strs[1]);
//                System.out.println("BigDecimal:"+pointra);
//                System.out.println("BigDecimal:"+pointde);

                Point point = new Point(pointra,pointde);
                pl.add(point);
//            }
                System.out.println("pointra:"+pl.get(0).getRa());
                System.out.println("pointde:"+pl.get(0).getDe());
//            Point point = new Point(Double.parseDouble(rs2.getString(2)),
//                    Double.parseDouble(rs2.getString(3)));

            /**
             * RA最大最小值差+最大的EPOS
             * Epos(mas) 转换成 deg = EPOS / 3600000
             */
            String sql2= "select MAX(epos) from ucactest where pixid = '116840'";

            ps = connection.prepareStatement(sql2);
            ResultSet rs3 = ps.executeQuery();
            System.out.println(rs3);

            double epos = 0;

            while (rs3.next()) {
                //如果有数据，取第一列添加如list
                epos = (rs3.getInt(1));
            }
            epos = epos / 3600000;
            System.out.println(epos);
            epos = x + epos;
            System.out.println("epos:"+epos);

            /**
             * 算距离误差，KNN近似查询
             */
            BigDecimal err = new BigDecimal(epos);
            System.out.println("系统误差为："+err);

//            sql1[i] = " select * from u116840 where ra = '"+ura+"' ";

            String sql3= "select * from ucactest where point <-> point('"+strs[0]+"','"+strs[1]+"') < err";

            ps = connection.prepareStatement(sql3);
            ResultSet rs4 = ps.executeQuery();
            System.out.println(rs4);

            statement = connection.createStatement();

                }catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
            }
        }
}

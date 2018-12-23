package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

public class cross {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        Statement state = null;

        List<String> ucacra=new ArrayList<String>();
        List<String> ucacde=new ArrayList<String>();
        List<String> gaiara=new ArrayList<String>();
        List<String> gaiade=new ArrayList<String>();
        List<Ucac0> ul = new ArrayList<Ucac0>();
        List<Gaia0> gl = new ArrayList<Gaia0>();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/liuzhewei",
                            "liuzhewei", "123456");

            System.out.println("Opened database successfully");

            String sql1 = "select ra,de from u116840";

            PreparedStatement ps = connection.prepareStatement(sql1);

            ResultSet rs = ps.executeQuery();
            System.out.println(rs);

            String sql2 ="select ra,de from g116840";

            ps = connection.prepareStatement(sql2);

            ResultSet rs2 = ps.executeQuery();
            System.out.println(rs2);

            statement = connection.createStatement();

            while (rs.next()) {
                //如果有数据，取第一列添加如list
                ucacra.add(rs.getString(1));
                ucacde.add(rs.getString(2));
                Ucac0 u = new Ucac0(Double.parseDouble(rs.getString(1)),
                        Double.parseDouble(rs.getString(2)));
                ul.add(u);
            }

            while (rs2.next()) {
                gaiara.add(rs2.getString(1));
                gaiade.add(rs2.getString(2));
                Gaia0 g = new Gaia0(Double.parseDouble(rs2.getString(1)),
                        Double.parseDouble(rs2.getString(2)));
                gl.add(g);
            }

            System.out.println(ucacra);
            System.out.println(ucacde);
            System.out.println(gaiara);
            System.out.println(gaiade);

            rs.close();
            statement.close();
            connection.close();

        }catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        /**
         r = (de1 + de2)/2
         d = 根号(((ra1 + ra2)*cos(r))^2 + (de1 - de2)^2)
         d <= 3 * 根号(r1^2 + r2^2)
         系统误差：r1,r2
         */
        int n = ul.size();
        int m = gl.size();

        System.out.println(n);

        List<Match0> ml = new ArrayList<Match0>();


        if(n < m){

            for(int i=0; i<n; i++){

                ArrayList<Double> distance = new ArrayList<Double>();
                double ura = ul.get(i).getRa();
                double ude = ul.get(i).getDe();

                for(int j=0;j<m;j++){
                    double gra = gl.get(j).getRa();
                    double gde = gl.get(j).getDe();
                    double r = (ude + gde) / 2;
                    double x = ura + gra;
                    double de = ude - gde;
                    double ra = x * Math.cos(r);
                    double y = Math.pow(ra,2) + Math.pow(de,2);
                    double d = Math.sqrt(y);
                    distance.add(d);
//                    System.out.println(d);
                }

                int t= 0;
                double min = distance.get(0);

                for(int k=1; k< distance.size();k++){
                    if(distance.get(k) < min){
                        t = k;
                        min = distance.get(k);
                    }
                }
//                System.out.println("t:"+t);
//                System.out.println("size:"+gl.size());
                System.out.println(gl.get(t).getRa());
                System.out.println(gl.get(t).getDe());
                System.out.println(min);

                Match0 match = new Match0(ura,ude,gl.get(t).getRa(),gl.get(t).getDe(),min);
                ml.add(match);
            }
            System.out.println(ml.size());
            System.out.println(ml.get(0).getGra());
            System.out.println(ml.get(0).getUra());
        }

        /**
         * 匹配结束合并两表
         */

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/liuzhewei",
                            "liuzhewei", "123456");
            System.out.println("Opened database successfully");

            String sql1[] = new String[ml.size()];
            String sql2[] = new String[ml.size()];

            for(int i=0; i<ml.size(); i++){
                String ura = new String(String.valueOf(ml.get(i).getUra()));
                String gra = new String(String.valueOf(ml.get(i).getGra()));
                String dis = new String(String.valueOf(ml.get(i).getDis()));
                sql1[i] = " select * from u116840 where ra = '"+ura+"' ";

                PreparedStatement ps = connection.prepareStatement(sql1[i]);
                ResultSet rs = ps.executeQuery();
//                System.out.println(rs);

                sql2[i] = " select * from g116840 where ra = '"+gra+"' ";

                ps = connection.prepareStatement(sql2[i]);

                ResultSet rs2 = ps.executeQuery();
                System.out.println(rs2);

                List<String> list1=new ArrayList<String>();
                List<String> list2=new ArrayList<String>();

                while (rs.next()) {
                    //如果有数据，取第一列添加如list
                    list1.add(rs.getString(1).trim());
                    list1.add(rs.getString(2).trim());
                    list1.add(rs.getString(3).trim());
                    list1.add(rs.getString(4).trim());
                    list1.add(rs.getString(5).trim());
                    list1.add(rs.getString(6).trim());
                    list1.add(rs.getString(7).trim());
                    list1.add(rs.getString(8).trim());
                    list1.add(rs.getString(9).trim());
                    list1.add(rs.getString(10).trim());
                    list1.add(rs.getString(11).trim());
                    list1.add(rs.getString(12).trim());
//                    list1.add(rs.getString(13).trim());
                }
                System.out.println(list1);

                while (rs2.next()) {
                    //如果有数据，取第一列添加如list
                    list2.add(rs2.getString(1).trim());
                    list2.add(rs2.getString(2).trim());
                    list2.add(rs2.getString(3).trim());
                    list2.add(rs2.getString(4).trim());
                    list2.add(rs2.getString(5).trim());
                    list2.add(rs2.getString(6).trim());
                    list2.add(rs2.getString(7).trim());
                    list2.add(rs2.getString(8).trim());
                    list2.add(rs2.getString(9).trim());
                    list2.add(rs2.getString(10).trim());
                    list2.add(rs2.getString(11).trim());
                    list2.add(rs2.getString(12).trim());
                    list2.add(rs2.getString(13).trim());
                }
                System.out.println(list2);

                /**
                 * 合并UCAC和GAIA两个列表
                 */

                List<String> list=new ArrayList<String>();
                list.add(dis);
                list.addAll(list1);
                list.addAll(list2);

                System.out.println("现在集合内容:"+list);

                statement = connection.createStatement();

//                ps = connection.prepareStatement(sql2);
//                ResultSet rs2 = ps.executeQuery();
//                System.out.println(rs2);

//                System.out.println(sql[i]);
//                statement = connection.createStatement();
//                statement.execute(sql[i]);
//                ResultSet rs = statement.executeQuery(sql[i]);
//                rs.close();
//                statement.close();
//                connection.close();

                String sql  = "insert into match116840 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                ps = connection.prepareStatement(sql);
                for (int j = 0; j < list.size(); j++) {
                    ps.setString(j+1,list.get(j));
                }
                ps.executeUpdate();

            }
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
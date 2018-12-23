package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class tableCreate {
    public static void main( String args[] )
    {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/liuzhewei",
                            "liuzhewei", "123456");
            System.out.println("Opened database successfully");

            statement = connection.createStatement();

            /*
            循环建表
             */
//            for(int i=0; i<60; i++) {
//                String sql = "CREATE TABLE ucac"+i+" "+
//                        "(ID  CHAR(50) PRIMARY KEY     NOT NULL," +
//                        " RA            double precision      NOT NULL," +
//                        " ERA           CHAR(50)   NOT NULL, " +
//                        " DE            double precision    NOT NULL, " +
//                        " EDE            CHAR(50)    NOT NULL, " +
//                        " EPOS            CHAR(50)    NOT NULL, " +
//                        " PMRA            CHAR(50)    NOT NULL, " +
//                        " EPMRA            CHAR(50)    NOT NULL, " +
//                        " PMDE            CHAR(50)    NOT NULL, " +
//                        " EPMDE            CHAR(50)    NOT NULL, " +
//                        " PIXID            CHAR(50)    NOT NULL, " +
//                        " UCAC            CHAR(50)    NOT NULL  ," +
//                        " POINT            POINT     NOT NULL)";
//                statement.executeUpdate(sql);
//            }

            /*
            循环删表
             */

            for(int i=0; i<60; i++) {
                String sql = "drop TABLE gaia"+i+" ";
                statement.executeUpdate(sql);
            }

            statement.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table deleted successfully");
    }

}

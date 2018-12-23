package test;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class createTable {

        public static void main( String args[] ) {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/testdb",
                                "liuzhewei", "123456");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();

//                for(int i=0;i<59;i++) {
//                    String sql = "CREATE TABLE g116840" +
//                            "(DIS   CHAR(50)  NOT NULL," +
//                            " UID   CHAR(50) PRIMARY KEY     NOT NULL," +
//                            " URA            CHAR(50)      NOT NULL," +
//                            " EURA           CHAR(50)   NOT NULL, " +
//                            " UDE            CHAR(50)    NOT NULL, " +
//                            " EUDE            CHAR(50)    NOT NULL, " +
//                            " EPOS            CHAR(50)    NOT NULL, " +
//                            " UPMRA            CHAR(50)    NOT NULL, " +
//                            " EUPMRA            CHAR(50)    NOT NULL, " +
//                            " UPMDE            CHAR(50)    NOT NULL, " +
//                            " EUPMDE            CHAR(50)    NOT NULL, " +
//                            " UPIXID            CHAR(50)    NOT NULL, " +
//                            " UCAC            CHAR(50)    NOT NULL  ," +
////                        " POINT            POINT     NOT NULL" +
//                            " GID           CHAR(50)   NOT NULL," +
//                            " GRA            CHAR(50)      NOT NULL," +
//                            " EGRA           CHAR(50)   NOT NULL, " +
//                            " GDE            CHAR(50)    NOT NULL, " +
//                            " EGDE            CHAR(50)    NOT NULL, " +
//                            " SOURCE            CHAR(50)    NOT NULL, " +
//                            " GPMRA            CHAR(50)    NOT NULL, " +
//                            " EGPMRA            CHAR(50)    NOT NULL, " +
//                            " GPMDE            CHAR(50)    NOT NULL, " +
//                            " EGPMDE            CHAR(50)    NOT NULL, " +
//                            " GMAG            CHAR(50)    NOT NULL, " +
//                            " EGMAG            CHAR(50)    NOT NULL, " +
//                            " GPIXID            CHAR(50)    NOT NULL)";

                String sql = "CREATE TABLE ucactest" +
                        "(ID   CHAR(50) PRIMARY KEY     NOT NULL," +
                        " RA            double precision      NOT NULL," +
                        " ERA           CHAR(50)   NOT NULL, " +
                        " DE            double precision    NOT NULL, " +
                        " EDE            CHAR(50)    NOT NULL, " +
                        " EPOS            INT    NOT NULL, " +
                        " PMRA            CHAR(50)    NOT NULL, " +
                        " EPMRA            CHAR(50)    NOT NULL, " +
                        " PMDE            CHAR(50)    NOT NULL, " +
                        " EPMDE            CHAR(50)    NOT NULL, " +
                        " PIXID            CHAR(50)    NOT NULL, " +
                        " UCAC            CHAR(50)    NOT NULL  ," +
                        " POINT            POINT     NOT NULL)" ;

//                String sql = "CREATE TABLE g116840 " +
//                        "(ID  CHAR(50) PRIMARY KEY     NOT NULL," +
//                        " RA            double precision      NOT NULL," +
//                        " ERA           CHAR(50)   NOT NULL, " +
//                        " DE            double precision    NOT NULL, " +
//                        " EDE            CHAR(50)    NOT NULL, " +
//                        " SOURCE            CHAR(50)    NOT NULL, " +
//                        " PMRA            CHAR(50)    NOT NULL, " +
//                        " EPMRA            CHAR(50)    NOT NULL, " +
//                        " PMDE            CHAR(50)    NOT NULL, " +
//                        " EPMDE            CHAR(50)    NOT NULL, " +
//                        " GMAG            CHAR(50)    NOT NULL, " +
//                        " EGMAG            CHAR(50)    NOT NULL, " +
//                        " PIXID            CHAR(50)    NOT NULL)";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    c.close();
//                }
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                System.exit(0);
            }
            System.out.println("Table created successfully");
        }

}

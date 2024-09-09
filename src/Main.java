import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root"; // user name
    static final String PASS = ""; // user password
    static final String QUERY = "select building,capacity from room where number = '101'"; // input query
    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt= null;
        ResultSet rs= null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                System.out.println("Building " + rs.getString("building") + " capacity = " + rs.getInt("capacity"));
            }
        }catch (SQLException sqle){
            System.out.println("SQLException : "+ sqle);
        }finally {
            try {
                if (rs != null) rs.close();  // Null 체크 추가
                if (stmt != null) stmt.close();  // Null 체크 추가
                if (conn != null) conn.close();
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
        }
    }
}
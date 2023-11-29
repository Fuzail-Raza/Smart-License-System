import java.sql.*;

class test{

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root","root","root");
        Statement stmt=con.createStatement();
        ResultSet s=stmt.executeQuery("Select * from oracle");

    }

}

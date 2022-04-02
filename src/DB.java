import java.net.SocketOption;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

public class DB implements AutoCloseable {

    private static final String JDBC_CONNECTION_STRING ="jdbc:mysql://localhost:3306/project";
    private Connection connection = null;

    public DB() {
        try {
            this.connection = DriverManager.getConnection(JDBC_CONNECTION_STRING, "root", "root123");
        } catch (SQLException var2) {
            this.error(var2);
        }
    }

    public void testfunc(){

        try {
            Statement s = this.connection.createStatement();
            // ResultSet results = s.executeQuery("SELECT name from hashkeylist");


        } catch (SQLException var4) {
            this.error(var4);
        }
    }

    //http://localhost:8088/SOS?ID=00000001
    //String URL =
    //String get = url.getFile();
    public void Auth(String user, String pass){
            System.out.println(user);
        try {
            Statement s = this.connection.createStatement();
            PAC pac = new PAC();
            String passw = "SELECT * from records where name = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(passw);
            preparedStatement.setString(1, user);
            ResultSet pa = preparedStatement.executeQuery();
            String p="yes";

            while(pa.next()){

              p = pa.getString(3);


            }
            pac.username = user;
            pac.password = p;
            System.out.println(p);


            int out =  pac.authenticate(user,pass);
            if(out == 1){
                System.out.println("Welcome!");
            }
            else{
                System.out.println("Username/password is wrong");
            }

        } catch (SQLException var4) {
            this.error(var4);
        }

    }

    public String SOS(String ID){
        try {
            Statement s = this.connection.createStatement();
            String ans = "yes";

            String sql = "SELECT * from records where ID = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            ResultSet results = preparedStatement.executeQuery();
            while(results.next()){

                 ans = results.getString(1);
                 System.out.println(ans);
                 ans = results.getString(2);
                 System.out.println(ans);
                 ans = results.getString(3);
                 System.out.println(ans);
                 ans = results.getString(4);
                 System.out.println(ans);
                 ans = results.getString(5);
                 System.out.println(ans);
                 ans = results.getString(6);
                 System.out.println(ans);
                 ans = results.getString(7);
                 System.out.println(ans);



            }
            // String ans = results.getString(1);
            System.out.println(ans);
            return ans;
        }
        catch (SQLException var4){
            this.error(var4);

        }
        return "false";
    }

    public void close() {
        try {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException var2) {
            this.error(var2);
        }
    }
    private void error(SQLException sqle) {
        System.err.println("Problem Opening Database! " + sqle.getClass().getName());
        sqle.printStackTrace();
        System.exit(1);
    }
}
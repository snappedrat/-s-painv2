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

        try {
            Statement s = this.connection.createStatement();
            PAC pac = new PAC();
            String passw = "SELECT password from records where username = ? ";
            PreparedStatement preparedStatement = this.connection.prepareStatement(passw);
            preparedStatement.setString(1, user);
            ResultSet pa = preparedStatement.executeQuery();
            String p=null;

            while(pa.next()){

              p = pa.getString(pa.findColumn("password"));


            }
            pac.username = user;
            pac.password = p;

            int out =  pac.authenticate(user,pass);
            if(out == 1){

            }

        } catch (SQLException var4) {
            this.error(var4);
        }

    }

    public String SOS(String ID){
        try {
            Statement s = this.connection.createStatement();

            String sql = "SELECT * from records where ID = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            ResultSet results = preparedStatement.executeQuery();

            String ans = results.toString();
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
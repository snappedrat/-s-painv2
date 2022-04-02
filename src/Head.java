import java.util.*;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.get;
import static spark.Spark.port;
import java.sql.*;

public class Head {
    public static void main(String args[]){
        // DB db = new DB();

        port(8088); //port

        get("/test", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                String signal = request.queryParams("info"); // test
                Boolean Flag = false;

                System.out.println(signal);

                return true;
            }
        });

        get("/test", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                String signal = request.queryParams("info"); // test
                Boolean Flag = false;

                System.out.println(signal);

                return true;
            }
        });
        get("/login", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                String user = request.queryParams("username"); //get user
                String pass = request.queryParams("password"); //get pass
                DB db = new DB();
                db.Auth(user,pass);
                Boolean Flag = false;



                return true;
            }
        });


        //http://localhost:8088/SOS?ID=00000001
        //http://localhost:3306/SOS?ID=00000001
        get("/SOS", new Route() {

            public Object handle (Request request, Response response) throws Exception {
                String ID = request.queryParams("ID"); //get ID
                DB db = new DB();
                db.SOS(ID);
                return true;


            }
        });


        //http://localhost:8088/noemg?ID=00000001&dest=13.075770020713888,80.22721666704432&z - wronggg
        get("/nonemg", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                String signal = request.queryParams("info"); // test
                Boolean Flag = false;

                System.out.println(signal);

                return true;
            }
        });

    }
}

package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_DB {
    private static String dbname = "BatiCuisine";
    private static String user = "GreenPulse";
    private static String pass = "";


    private static Connection_DB instance;
    private static Connection conn;

public static Connection_DB getInstance(){
    if(instance == null){
        instance = new Connection_DB();
    }
    return instance;
}
    public Connection_DB() {}
    public Connection Connect_to_DB() {
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.dbname, this.user, this.pass);

                if (conn != null) {
                    System.out.println("Connected successfully");
                } else {
                    System.out.println("Connection failed");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return conn;
    }



}

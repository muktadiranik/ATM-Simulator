import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connection {
    Connection c;
    Statement s;

    public connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///atm", "root", "");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

import java.sql.ResultSet;
import java.util.Collections;
import java.util.Vector;

public class test {
    public static void main(String[] args) {
        connection c = new connection();
        Vector<Integer> cardNums = new Vector<Integer>();
        String q = "select cardNum from registration";
        try {
            ResultSet result = c.s.executeQuery(q);
            while (result.next()) {
                cardNums.add(result.getInt("cardNum"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        for (int i : cardNums) {
            System.out.println(i);
        }

        System.out.println(Collections.max(cardNums));


    }
}

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBooks {

    public static void viewBooks() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from books");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("author") + " | " +
                                rs.getInt("quantity"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

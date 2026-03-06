import java.sql.*;
import java.util.Scanner;

public class SearchBook {

    public static void searchBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            String query = "SELECT * FROM books WHERE title LIKE ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "%" + title + "%");

            ResultSet rs = ps.executeQuery();

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

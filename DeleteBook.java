import java.sql.*;
import java.util.Scanner;

public class DeleteBook {

    public static void deleteBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM books WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Book Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
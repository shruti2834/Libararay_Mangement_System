import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddBook {

    public static void addBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            String query = "insert into books(title,author,quantity) values(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, quantity);

            ps.executeUpdate();

            System.out.println("Book Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
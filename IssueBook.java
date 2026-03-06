import java.sql.*;
import java.util.Scanner;

public class IssueBook {

    public static void issueBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String student = sc.nextLine();

            // Check if same student already issued same book
            String checkStudentBook =
                    "SELECT * FROM issued_books WHERE book_id=? AND student_name=? AND return_date IS NULL";

            PreparedStatement ps1 = con.prepareStatement(checkStudentBook);

            ps1.setInt(1, bookId);
            ps1.setString(2, student);

            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {

                System.out.println("❌ This student already has this book issued.");
                return;
            }

            // Check book quantity
            String checkQty = "SELECT quantity FROM books WHERE id=?";

            PreparedStatement ps2 = con.prepareStatement(checkQty);

            ps2.setInt(1, bookId);

            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {

                int quantity = rs2.getInt("quantity");

                if (quantity <= 0) {

                    System.out.println("❌ Book not available.");
                    return;
                }

                Date issueDate = new Date(System.currentTimeMillis());

                // Issue book
                String issueQuery =
                        "INSERT INTO issued_books(book_id, student_name, issue_date) VALUES(?,?,?)";

                PreparedStatement ps3 = con.prepareStatement(issueQuery);

                ps3.setInt(1, bookId);
                ps3.setString(2, student);
                ps3.setDate(3, issueDate);

                ps3.executeUpdate();

                // Reduce quantity
                String updateQty =
                        "UPDATE books SET quantity = quantity - 1 WHERE id=?";

                PreparedStatement ps4 = con.prepareStatement(updateQty);

                ps4.setInt(1, bookId);

                ps4.executeUpdate();

                System.out.println("✅ Book Issued Successfully");

            } else {

                System.out.println("❌ Book ID not found");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
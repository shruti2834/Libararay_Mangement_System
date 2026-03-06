import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnBook {

    public static void returnBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Issue ID: ");
            int issueId = sc.nextInt();

            String query = "SELECT book_id, issue_date, return_date FROM issued_books WHERE issue_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, issueId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int bookId = rs.getInt("book_id");
                Date issueDate = rs.getDate("issue_date");
                Date returnDateDB = rs.getDate("return_date");

                // Check if already returned
                if (returnDateDB != null) {

                    System.out.println("❌ Book already returned!");
                    return;
                }

                LocalDate issue = issueDate.toLocalDate();
                LocalDate today = LocalDate.now();

                long days = ChronoUnit.DAYS.between(issue, today);

                long fine = 0;

                if (days > 7) {
                    fine = (days - 7) * 10;
                }

                System.out.println("Fine Amount: ₹" + fine);

                Date returnDate = Date.valueOf(today);

                // Update return date
                String updateQuery = "UPDATE issued_books SET return_date=? WHERE issue_id=?";

                PreparedStatement ps2 = con.prepareStatement(updateQuery);

                ps2.setDate(1, returnDate);
                ps2.setInt(2, issueId);

                ps2.executeUpdate();

                // Increase quantity only once
                String updateQty = "UPDATE books SET quantity = quantity + 1 WHERE id=?";

                PreparedStatement ps3 = con.prepareStatement(updateQty);

                ps3.setInt(1, bookId);

                ps3.executeUpdate();

                System.out.println("✅ Book Returned Successfully");

            } else {

                System.out.println("❌ Issue ID not found");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
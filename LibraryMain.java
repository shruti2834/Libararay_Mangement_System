import java.util.Scanner;

public class LibraryMain {

    public static void main(String[] args) {

        if (!AdminLogin.login()) {
            return;
        }

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== LIBRARY SYSTEM =====");

            System.out.println("1 Add Book");
            System.out.println("2 View Books");
            System.out.println("3 Search Book");
            System.out.println("4 Delete Book");
            System.out.println("5 Issue Book");
            System.out.println("6 Return Book");
            System.out.println("7 Exit");

            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AddBook.addBook();
                    break;

                case 2:
                    ViewBooks.viewBooks();
                    break;

                case 3:
                    SearchBook.searchBook();
                    break;

                case 4:
                    DeleteBook.deleteBook();
                    break;

                case 5:
                    IssueBook.issueBook();
                    break;

                case 6:
                    ReturnBook.returnBook();
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
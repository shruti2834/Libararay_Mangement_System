import java.util.Scanner;

public class AdminLogin {

    static int attempts = 0;
    static long lockTime = 0;

    public static boolean login() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            // Check if account is locked
            if (attempts >= 5) {

                long currentTime = System.currentTimeMillis();

                if (currentTime - lockTime < 60000) {

                    long remaining = (60000 - (currentTime - lockTime)) / 1000;

                    System.out.println("Too many failed attempts.");
                    System.out.println("Try again after " + remaining + " seconds.");

                    return false;
                }
                else {
                    // Reset attempts after 1 minute
                    attempts = 0;
                }
            }

            System.out.println("\n===== ADMIN LOGIN =====");

            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            if (username.equals("admin") && password.equals("1234")) {

                System.out.println("Login Successful!");
                return true;
            }
            else {

                attempts++;

                System.out.println("Invalid username or password.");

                System.out.println("Attempts left: " + (5 - attempts));

                if (attempts == 5) {

                    lockTime = System.currentTimeMillis();

                    System.out.println("Login locked for 1 minute.");
                }
            }
        }
    }
}
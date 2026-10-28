
import java.util.*;

public class OnlineFoodOrderingSystem {
    static Scanner sc = new Scanner(System.in);
    static String[] items = {"Burger", "Pizza", "Sandwich", "Fries"};
    static int[] prices = {120, 250, 100, 80};
    static ArrayList<String> history = new ArrayList<>();
    static int orderId = 100;
    static double sales = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Menu  2.Order  3.Track Order");
            System.out.println("4.Order History  5.Sales Report  6.Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: showMenu(); break;
                case 2: placeOrder(); break;
                case 3: trackOrder(); break;
                case 4:
                    for (String s : history)
                        System.out.println(s);
                    break;
                case 5:
                    System.out.println("Total Sales: Rs." + sales);
                    System.out.println("Total Orders: " + history.size());
                    break;
                case 6: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void showMenu() {
        for (int i = 0; i < items.length; i++)
            System.out.println((i + 1) + ". " + items[i] +
                               " - Rs." + prices[i]);
    }

    static void placeOrder() {
        sc.nextLine();
        System.out.print("Customer name: ");
        String name = sc.nextLine();
        showMenu();
        double total = 0;
        System.out.println("Enter item number (0 to finish):");

        while (true) {
            int item = sc.nextInt();
            if (item == 0) break;
            if (item < 1 || item > items.length) {
                System.out.println("Invalid item!");
                continue;
            }
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            if (qty <= 0) {
                System.out.println("Invalid quantity!");
                continue;
            }
            double cost = prices[item - 1] * qty;
            total += cost;
            System.out.println(items[item - 1] +
                               " x " + qty + " = Rs." + cost);
        }

        if (total == 0) {
            System.out.println("No items ordered.");
            return;
        }

        orderId++;
        String record = "Order ID: " + orderId + ", Customer: " +
                        name + ", Total: Rs." + total +
                        ", Status: Placed";
        history.add(record);
        sales += total;
        System.out.println("\n----- BILL -----");
        System.out.println("Customer: " + name);
        System.out.println("Order ID: " + orderId);
        System.out.println("Total: Rs." + total);
        System.out.println("Status: Placed");
        System.out.println("Order placed successfully!");
    }

    static void trackOrder() {
        System.out.print("Enter Order ID: ");
        int id = sc.nextInt();
        boolean found = false;
        for (String s : history) {
            if (s.contains("Order ID: " + id + ",")) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("Order not found.");
    }
}

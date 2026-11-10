
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] products = {
            "Milk", "Bread", "Rice", "Sugar", "Apples", "Kiwi", "Grape"
        };

        int[] prices = {50, 50, 60, 60, 140, 120, 100};

        int[] quantities = new int[7];

        int choice;
        int quantity;

        System.out.println("================================");
        System.out.println("          GROCERY STORE");
        System.out.println("================================");
        System.out.println("\n========== SPECIAL OFFERS ==========");

        while (true) {

            System.out.println("\nPRODUCT LIST");

            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ". " + products[i]
                        + " - Rs." + prices[i]);
            }

            System.out.println("0. Checkout");

            System.out.print("\nEnter Product ID and Quantity (e.g., 1 2): ");

            choice = sc.nextInt();

            if (choice == 0) {
                break;
            }

            quantity = sc.nextInt();

            if (choice < 1 || choice > products.length) {
                System.out.println("Invalid Product ID!");
                continue;
            }

            if (quantity <= 0) {
                System.out.println("Enter a valid quantity!");
                continue;
            }

            quantities[choice - 1] += quantity;

            System.out.println(products[choice - 1]
                    + " added successfully!");
        }

        // Generate final bill

        int totalBill = 0;

        System.out.println("\n================================");
        System.out.println("           GROCERY BILL");
        System.out.println("================================");

        System.out.printf("%-12s %-12s %-12s%n",
                "Product", "Quantity", "Price");

        System.out.println("--------------------------------");

        for (int i = 0; i < products.length; i++) {

            if (quantities[i] > 0) {

                int amount = quantities[i] * prices[i];

                System.out.printf("%-12s %-12d Rs.%-10d%n",
                        products[i], quantities[i], amount);

                totalBill += amount;
            }
        }

        // Calculate discount

        // Calculate discount

double discount = 0;
int discountPercentage = 0;

if (totalBill >= 1000) {
    discountPercentage = 10;
    discount = totalBill * 0.10;
}
else if (totalBill >= 500) {
    discountPercentage = 5;
    discount = totalBill * 0.05;
}

// Calculate final amount

double finalAmount = totalBill - discount;

// Display final bill

System.out.println("--------------------------------");
System.out.println("Total Amount : Rs." + totalBill);
System.out.println("Discount     : Rs." + discount
        + " (" + discountPercentage + "%) - "
        + (discountPercentage == 10 ? "Above Rs.1000" :
           discountPercentage == 5 ? "Rs.500 - Rs.999" :
           "Below Rs.500"));
System.out.println("Final Amount : Rs." + finalAmount);
System.out.println("================================");
System.out.println("Thank you for shopping!");

        sc.close();
    }
}

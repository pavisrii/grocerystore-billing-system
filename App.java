import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] products = {
                "Milk", "Bread", "Rice", "Sugar",
                "Apples", "Kiwi", "Grape"
        };
        String[] categories = {
                "Dairy", "Bakery", "Grains", "Grains",
                "Fruits", "Fruits", "Fruits"
        };
        int[] prices = {50, 50, 60, 60, 140, 120, 100};
        int[] stock = {20, 30, 50, 40, 25, 20, 30};
        int[] quantities = new int[products.length];
        int choice;
        System.out.println("================================");
        System.out.println("          GROCERY STORE");
        System.out.println("================================");
        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Display Products & Add to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. Checkout");
            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Display Products and Add to Cart
                    System.out.println("\n========== PRODUCT LIST ==========");
                    System.out.printf("%-5s %-12s %-12s %-10s %-10s%n",
                            "ID", "Product", "Category", "Price", "Stock");
                    for (int i = 0; i < products.length; i++) {
                        System.out.printf("%-5d %-12s %-12s Rs.%-7d %-10d%n",
                                i + 1, products[i], categories[i],
                                prices[i], stock[i] - quantities[i]);
                    }
                    System.out.println("\nEnter 0 0 to return to Main Menu.");
                    while (true) {
                        System.out.print(
                                "\nEnter Product ID and Quantity (e.g., 2 2): ");
                        int id = sc.nextInt();
                        int quantity = sc.nextInt();
                        if (id == 0 && quantity == 0) {
                            break;
                        }
                        if (id < 1 || id > products.length) {
                            System.out.println("Invalid Product ID!");
                            continue;
                        }
                        int index = id - 1;
                        if (quantity <= 0) {
                            System.out.println("Enter a valid quantity!");
                        }
                        else if (quantities[index] + quantity > stock[index]) {
                            System.out.println("Insufficient stock!");
                            System.out.println("Available stock: "
                                    + (stock[index] - quantities[index]));
                        }
                        else {
                            quantities[index] += quantity;

                            System.out.println(products[index]
                                    + " added successfully!");
                        }
                    }
                    break;
                case 2:
                    // View Cart
                    System.out.println("\n========== YOUR CART ==========");
                    int cartTotal = 0;
                    boolean empty = true;
                    System.out.printf("%-12s %-10s %-10s%n",
                            "Product", "Quantity", "Amount");
                    for (int i = 0; i < products.length; i++) {
                        if (quantities[i] > 0) {
                            int amount = quantities[i] * prices[i];
                            System.out.printf("%-12s %-10d Rs.%-7d%n",
                                    products[i], quantities[i], amount);
                            cartTotal += amount;
                            empty = false;
                        }
                    }
                    if (empty) {
                        System.out.println("Your cart is empty!");
                    }
                    else {
                        System.out.println("--------------------------------");
                        System.out.println("Total Amount: Rs." + cartTotal);
                    }
                    break;
                case 3:
                    // Remove Selected Quantity from Cart
                    System.out.println("\n========== REMOVE PRODUCT ==========");
                    boolean cartEmpty = true;
                    System.out.printf("%-5s %-12s %-10s%n",
                            "ID", "Product", "Quantity");
                    for (int i = 0; i < products.length; i++) {
                        if (quantities[i] > 0) {
                            System.out.printf("%-5d %-12s %-10d%n",
                                    i + 1, products[i], quantities[i]);

                            cartEmpty = false;
                        }
                    }

                    if (cartEmpty) {
                        System.out.println("Your cart is empty!");
                        break;
                    }

                    System.out.print(
                            "\nEnter Product ID and Quantity to Remove (e.g., 6 1): ");
                    int removeId = sc.nextInt();
                    int removeQuantity = sc.nextInt();
                    if (removeId < 1 || removeId > products.length) {

                        System.out.println("Invalid Product ID!");
                    }
                    else if (removeQuantity <= 0) {

                        System.out.println("Enter a valid quantity!");

                    }
                    else if (quantities[removeId - 1] == 0) {

                        System.out.println("Product is not in your cart!");

                    }
                    else if (removeQuantity > quantities[removeId - 1]) {

                        System.out.println("You cannot remove more than the cart quantity!");

                        System.out.println("Available cart quantity: "
                                + quantities[removeId - 1]);
                    }
                    else {
                        quantities[removeId - 1] -= removeQuantity;
                        System.out.println(removeQuantity + " "
                                + products[removeId - 1]
                                + " removed successfully!");

                        System.out.println("Remaining quantity: "
                                + quantities[removeId - 1]);
                    }
                    break;
                case 4:
                    // Checkout
                    System.out.println("\n========== CHECKOUT ==========");
                    int totalBill = 0;
                    int totalItems = 0;
                    boolean hasProducts = false;
                    for (int i = 0; i < products.length; i++) {

                        if (quantities[i] > 0) {

                            hasProducts = true;

                            totalBill += quantities[i] * prices[i];
                            totalItems += quantities[i];
                        }
                    }
                    if (!hasProducts) {

                        System.out.println("Your cart is empty!");
                        break;
                    }
                    // Calculate Discount
                    double discount = 0;
                    int discountPercentage = 0;
                    if (totalBill >= 1000) {
                        discountPercentage = 10;
                        discount = totalBill * 0.10;
                    }
                    else if (totalBill > 500) {

                        discountPercentage = 4;
                        discount = totalBill * 0.04;
                    }
                    // Calculate GST
                    double amountAfterDiscount = totalBill - discount;

                    double gst = amountAfterDiscount * 0.05;

                    double finalAmount = amountAfterDiscount + gst;
                    // Generate Final Bill
                    System.out.println("\n================================");
                    System.out.println("           GROCERY BILL");
                    System.out.println("================================");

                    System.out.printf("%-12s %-10s %-10s%n",
                            "Product", "Quantity", "Amount");
                    System.out.println("--------------------------------");
                    for (int i = 0; i < products.length; i++) {
                        if (quantities[i] > 0) {
                            int amount = quantities[i] * prices[i];
                            System.out.printf("%-12s %-10d Rs.%-7d%n",
                                    products[i], quantities[i], amount);
                        }
                    }
                    System.out.println("--------------------------------");
                    System.out.println("Total Items      : " + totalItems);
                    System.out.println("Total Amount     : Rs." + totalBill);
                    System.out.printf("Discount         : Rs.%.2f (%d%%)%n",
                            discount, discountPercentage);
                    System.out.printf("GST (5%%)         : Rs.%.2f%n", gst);
                    System.out.printf("Final Amount     : Rs.%.2f%n",
                            finalAmount);
                    System.out.println("================================");
                    System.out.println("      THANK YOU FOR SHOPPING!");
                    System.out.println("================================");
                    // Update Stock after successful checkout
                    for (int i = 0; i < products.length; i++) {

                        stock[i] -= quantities[i];

                        quantities[i] = 0;
                    }
                    System.out.println("Stock updated successfully!");
                    System.out.println("Purchase completed successfully!");
                    System.out.println("\nStarting a new customer bill...");
                    break;
                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
}

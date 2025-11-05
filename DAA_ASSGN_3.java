//Arpita Patki 123B1F001
// Assignment 3 Implement the Fractional Knapsack algorithm to maximize the total utility value of the supplies transported.

import java.util.*;

class Item {
    String name;
    double value;
    double weight;
    boolean divisible;

    Item(String name, double value, double weight, boolean divisible) {
        this.name = name;
        this.value = value;
        this.weight = weight;
        this.divisible = divisible;
    }
}

public class DAA_ASSGN_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of relief items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for item " + (i + 1) + ":");
            System.out.print("Item name: ");
            String name = sc.next();
            System.out.print("Utility value: ");
            double value = sc.nextDouble();
            System.out.print("Weight (kg): ");
            double weight = sc.nextDouble();
            System.out.print("Is it divisible? (true/false): ");
            boolean div = sc.nextBoolean();

            items[i] = new Item(name, value, weight, div);
        }

        System.out.print("\nEnter boat capacity (in kg): ");
        double w = sc.nextDouble();

        // Sort by value/weight ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));

        double totalValue = 0, currWeight = 0;

        System.out.println("\nItems taken in the boat:");
        for (Item it : items) {
            if (currWeight + it.weight <= w) {
                currWeight += it.weight;
                totalValue += it.value;
                System.out.println(it.name + " -> 100% taken");
            } else {
                double remain = w - currWeight;
                if (remain > 0) {
                    if (it.divisible) {
                        double frac = remain / it.weight;
                        totalValue += it.value * frac;
                        currWeight += remain;
                        System.out.println(it.name + " -> " + (int)(frac * 100) + "% taken");
                    }
                }
                break;
            }
        }

        System.out.println("\nTotal utility value carried: " + totalValue);
        System.out.println("Total weight used: " + currWeight + " / " + w + " kg");
        sc.close();
    }
}


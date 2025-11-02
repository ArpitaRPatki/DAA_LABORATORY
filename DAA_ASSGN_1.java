//Arpita_Patki_123B1F001
//ASSIGNMENT 1


import java.util.*;

class CustomerOrder {
    String orderId;
    long timestamp; // Using long for large timestamp values

    public CustomerOrder(String orderId, long timestamp) {
        this.orderId = orderId;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OrderID: " + orderId + ", Timestamp: " + timestamp;
    }
}

public class DAA_ASSGN_1 {

    
    public static void mergeSort(CustomerOrder[] orders, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(orders, left, mid);
            mergeSort(orders, mid + 1, right);
            merge(orders, left, mid, right);
        }
    }

    
    private static void merge(CustomerOrder[] orders, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        CustomerOrder[] L = new CustomerOrder[n1];
        CustomerOrder[] R = new CustomerOrder[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = orders[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = orders[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        
        while (i < n1 && j < n2) {
            if (L[i].timestamp <= R[j].timestamp) {
                orders[k] = L[i];
                i++;
            } else {
                orders[k] = R[j];
                j++;
            }
            k++;
        }

        
        while (i < n1) {
            orders[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            orders[k] = R[j];
            j++;
            k++;
        }
    }

    
    public static void main(String[] args) {
        
        int n = 10;  // For demonstration, you can set n = 1_000_000
        CustomerOrder[] orders = new CustomerOrder[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            orders[i] = new CustomerOrder("ORD" + (i + 1), rand.nextInt(10000));
        }

        System.out.println("Before Sorting:");
        for (int i = 0; i < Math.min(10, n); i++) {
            System.out.println(orders[i]);
        }

        long startTime = System.nanoTime();
        mergeSort(orders, 0, orders.length - 1);
        long endTime = System.nanoTime();

        System.out.println("\nAfter Sorting:");
        for (int i = 0; i < Math.min(10, n); i++) {
            System.out.println(orders[i]);
        }

        System.out.println("\nTime taken: " + (endTime - startTime) / 1e6 + " ms");
    }
}

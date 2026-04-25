import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Order ID:");
        String orderId = sc.nextLine();

        System.out.println("\n--- Order Status ---");
        TrackOrderStatus.trackStatus(orderId);

        System.out.println("\n--- Order Details ---");
        ViewOrderDetails.viewDetails(orderId);
    }
}
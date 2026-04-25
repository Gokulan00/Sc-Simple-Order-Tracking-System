import com.google.cloud.firestore.*;

public class ViewOrderDetails {

    public static void viewDetails(String orderId) throws Exception {

        Firestore db = FirebaseConfig.getFirestore();

        DocumentReference docRef = db.collection("orders").document(orderId);

        DocumentSnapshot document = docRef.get().get();

        if (document.exists()) {

            System.out.println("Customer: " + document.getString("customer_name"));
            System.out.println("Product: " + document.getString("product"));
            System.out.println("Quantity: " + document.getLong("quantity"));
            System.out.println("Status: " + document.getString("status"));
            System.out.println("Order Date: " + document.getTimestamp("order_date"));

        } else {

            System.out.println("Order not found");

        }
    }
}
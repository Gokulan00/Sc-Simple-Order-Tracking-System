import com.google.cloud.firestore.*;

public class TrackOrderStatus {

    public static void trackStatus(String orderId) throws Exception {

        Firestore db = FirebaseConfig.getFirestore();

        DocumentReference docRef = db.collection("orders").document(orderId);

        DocumentSnapshot document = docRef.get().get();

        if (document.exists()) {

            String status = document.getString("status");

            System.out.println("Order Status: " + status);

        } else {

            System.out.println("Order not found");

        }
    }
}
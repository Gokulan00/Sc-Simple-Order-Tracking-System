import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.cloud.firestore.*;

import java.io.IOException;
import java.io.OutputStream;

public class OrderHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("🔥 API HIT 🔥");

        String path = exchange.getRequestURI().getPath();
        String[] parts = path.split("/");

        String response = "";

        try {

            if (parts.length < 3) {
                response = "{ \"error\": \"Invalid URL\" }";
            } else {

                String orderId = parts[2];
                System.out.println("Order ID: " + orderId);

                Firestore db = FirebaseConfig.getFirestore();

                DocumentSnapshot doc = db.collection("orders")
                        .document(orderId)
                        .get()
                        .get();

                if (doc.exists()) {
                    response = "{ \"status\": \"" + doc.getString("status") + "\", " +
                               "\"product\": \"" + doc.getString("product") + "\" }";
                } else {
                    response = "{ \"error\": \"Order not found\" }";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "{ \"error\": \"Server error\" }";
        }

        // 🔥 CORS FIX
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {

    private static Firestore db;

    public static Firestore getFirestore() throws Exception {

        if (db == null) {

            // 🔥 NO FILE PATH HERE
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();

            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();
        }

        return db;
    }
}
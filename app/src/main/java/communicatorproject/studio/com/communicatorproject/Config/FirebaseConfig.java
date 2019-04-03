package communicatorproject.studio.com.communicatorproject.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseConfig {

    private static DatabaseReference firebaseReference;
    private static FirebaseAuth auth;

    public static DatabaseReference getFirebase(){

        if (firebaseReference == null){
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseReference;
    }

    public static FirebaseAuth getFirebaseAuthentication(){

        if(auth == null){

            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

}

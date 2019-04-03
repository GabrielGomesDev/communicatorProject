package communicatorproject.studio.com.communicatorproject.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import communicatorproject.studio.com.communicatorproject.Config.FirebaseConfig;
import communicatorproject.studio.com.communicatorproject.Model.User;
import communicatorproject.studio.com.communicatorproject.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private EditText email;
    private Button register;
    private User user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.nameRegId);
        password = findViewById(R.id.passwRegId);
        email = findViewById(R.id.emailRegId);
        register = findViewById(R.id.regButtonId);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new User();
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                registerUser();

            }
        });

    }
    //Registering a user
    private void registerUser(){

        auth = FirebaseConfig.getFirebaseAuthentication();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                    FirebaseUser firebaseUser = task.getResult().getUser();
                    user.setId(firebaseUser.getUid());
                    user.Save();

                    auth.signOut();
                    finish();

                } else {

                    String exceptionError = "";

                    try {
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e){
                        exceptionError = "Type a stronger password using numbers and special characters.";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        exceptionError = "The e-mail is invalid.";
                    } catch (FirebaseAuthUserCollisionException e){
                        exceptionError = "A user with the provided e-mail already exists.";
                    } catch (Exception e){
                        exceptionError = "Registration unsuccesful.";
                    }

                    Toast.makeText(RegistrationActivity.this, "Error: " + exceptionError, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

package communicatorproject.studio.com.communicatorproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import communicatorproject.studio.com.communicatorproject.Config.FirebaseConfig;
import communicatorproject.studio.com.communicatorproject.Model.User;
import communicatorproject.studio.com.communicatorproject.R;

public class LoginActivity extends Activity {

    private EditText email;
    private EditText password;
    private Button login;
    private User user;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verifyLogin();

        email = findViewById(R.id.emailLogId);
        password = findViewById(R.id.passwordLogId);
        login = findViewById(R.id.logInButtonId);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new User();

                user.setEmail( email.getText().toString());
                user.setPassword( password.getText().toString());
                validateLogin();

            }
        });

    }
     public void validateLogin(){

         firebaseAuth = FirebaseConfig.getFirebaseAuthentication();
         firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {

                 if( task.isSuccessful()){

                     openMainActivity();

                     Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                 } else {

                     Toast.makeText(LoginActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();

                 }
             }
         });
     }

     private void verifyLogin(){

        firebaseAuth = FirebaseConfig.getFirebaseAuthentication();
        if( firebaseAuth.getCurrentUser() != null){

            openMainActivity();

        }
     }

     private void openMainActivity(){

        Intent intent = new Intent (LoginActivity.this, MainActivity.class);
        startActivity( intent );
        finish();

     }

    public void openUserRegister (View view){

        Intent intent = new Intent (LoginActivity.this, RegistrationActivity.class);

        startActivity(intent);
    }

}




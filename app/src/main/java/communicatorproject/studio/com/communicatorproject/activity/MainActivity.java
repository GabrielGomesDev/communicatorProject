package communicatorproject.studio.com.communicatorproject.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;


import com.google.firebase.auth.FirebaseAuth;

import communicatorproject.studio.com.communicatorproject.Config.FirebaseConfig;
import communicatorproject.studio.com.communicatorproject.R;

public class MainActivity extends AppCompatActivity {

    //private Button logout;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth userAuthentication;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarMainId);
        toolbar.setTitle("Communicator");
        setSupportActionBar( toolbar );

        userAuthentication = FirebaseConfig.getFirebaseAuthentication();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.item_leave:
                logOff();
                return true;
            case R.id.action_settings:
                return true;
             default:
                 return super.onOptionsItemSelected(item);

        }
    }

    public void logOff(){

        userAuthentication.signOut();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}

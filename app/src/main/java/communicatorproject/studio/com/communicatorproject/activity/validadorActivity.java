package communicatorproject.studio.com.communicatorproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import communicatorproject.studio.com.communicatorproject.R;
import communicatorproject.studio.com.communicatorproject.helper.preferences;

public class validadorActivity extends AppCompatActivity {

    private EditText validationCode;
    private Button validationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        validationCode = findViewById(R.id.validCodId);
        validationButton = findViewById(R.id.verifyButtonId);

        SimpleMaskFormatter simpleMaskCode = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher codeMaskWatcher = new MaskTextWatcher(validationCode, simpleMaskCode);

        validationCode.addTextChangedListener( codeMaskWatcher );

        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferences preferences = new preferences(validadorActivity.this);

                HashMap<String, String> user = preferences.getUserData();

                String generatedToken = user.get("token");
                String typedCode = validationCode.getText().toString();

                if( generatedToken.equals(typedCode)){

                    Toast.makeText(validadorActivity.this, "Valid Token", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(validadorActivity.this, "Invalid Token", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
